package com.se310.store.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Basket class implementation representing Customer basket
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Basket {

    private String id;
    private final Map<String, Integer> productMap;
    private Customer customer;
    private Store store;

    /**
     * Constructor for Basket class
     * @param id
     */
    public Basket(String id) {
        this.id = id;
        this.productMap = new HashMap<>();
    }

    /**
     * Getter method for Basket id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for Basket id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to add Product to the Customer's Basket. It throws StoreModel Exception on
     * various model inconsistencies
     * Method is synchronized to guarantee critical section
     * @param productId
     * @param count
     * @throws StoreException
     */
    synchronized public void addProduct(String productId, int count) throws StoreException {

        //Make sure that the customer is registered
        if(customer.getType() == CustomerType.guest){
            throw new StoreException("Add Product", "Guests Are Not Allowed to Shop");
        }

        //Get location of the customer associated with this basket
        StoreLocation location = this.customer.getStoreLocation();
        //Get the aisle where the customer was last seen
        Aisle aisle = store.getAisle(location.getAisleId());

        //Check to see if exists
        if(aisle == null){
            throw new StoreException("Add Product", "Aisle Does Not Exist");
        }

        //Get all inventory items from the shelves in the aisle where customer was last seen
        List<Inventory> inventoryList = aisle.getShelfMap().values()
                .stream()
                .flatMap(shelf -> shelf.getInventoryMap().values().
                        stream())
                .filter(inventory -> productId.equals(inventory.getProductId()))
                        .collect(Collectors.toList());

        //If inventory list is empty that means product is not available to be put in the basket
        if(inventoryList.isEmpty()){
            System.out.println("\u001B[31m" + "Error : " + customer + "\u001B[0m");
            throw new StoreException("Add Product", "Customer Is Not Near Product");
        }

        //If inventory list is larger than one that means that there are multiple product are available
        //where customer was last seen
        if(inventoryList.size() > 1){
            System.out.println("\u001B[31m" + "Error : " + inventoryList + "\u001B[0m");
            throw new StoreException("Add Product", "There Are Several Products In the Aisle");
        }

        //If the count of the product on the shelf is smaller than the customer is trying to buy throw and exception
        Inventory inventory = inventoryList.get(0);
        if((inventory.getCount() - count) < 0){
            System.out.println("\u001B[31m" + "Error : " + inventory + "\u001B[0m");
            throw new StoreException("Add Product", "There Is Not Enough Inventory on the Shelf");
        }

        //Put the product in the basket and decrement product on the shelf
        this.productMap.merge(productId, count, Integer::sum);
        inventory.setCount(inventory.getCount() - count);
    }

    /**
     * Remove Product from the Customer's Basket. It throws StoreModel Exception on
     * various model inconsistencies
     * Method is synchronized to guarantee critical section
     * @param productId
     * @param count
     * @throws StoreException
     */
    synchronized public void removeProduct(String productId, int count) throws StoreException {

        //If Customer is trying to remove more units of the products from the basket than he/she has put in
        //throw an exception
        Integer tempCount = this.productMap.get(productId);
        if(tempCount == null){
            throw new StoreException("Remove Product", "Product Does Not Exist");
        } else if (count > tempCount){
            throw new StoreException("Remove Product", "Trying To Remove More Quantity Than Exists");
        }

        //Get location of the customer associated with this basket
        StoreLocation location = this.customer.getStoreLocation();
        //Get the aisle where the customer was last seen
        Aisle aisle = store.getAisle(location.getAisleId());

        //Check to see if exists
        if(aisle == null){
            throw new StoreException("Remove Product", "Aisle Does Not Exist");
        }

        //Get all inventory items from the shelves in the aisle where customer was last seen
        List<Inventory> inventoryList = aisle.getShelfMap().values()
                .stream()
                .flatMap(shelf -> shelf.getInventoryMap().values().
                        stream())
                .filter(inventory -> productId.equals(inventory.getProductId()))
                .collect(Collectors.toList());

        //If inventory list is empty that means product is not available to be put on the shelf
        if(inventoryList.isEmpty()){
            System.out.println("\u001B[31m" + "Error: " + customer + "\u001B[0m");
            throw new StoreException("Remove Product", "Customer Is Not Near Product");
        }

        //If inventory list is larger than one that means that there are multiple product slots are available
        //on the shelves where customer was last seen
        if(inventoryList.size() > 1){
            System.out.println("\u001B[31m" + "Error : " + inventoryList + "\u001B[0m");
            throw new StoreException("Remove Product", "There Are Several Products In the Aisle");
        }

        //If product capacity on the shelf is smaller than what the customer is trying to put back throw and exception
        Inventory inventory = inventoryList.get(0);
        if((inventory.getCount() + count) > inventory.getCapacity()){
            System.out.println("\u001B[31m" + "Error : " + inventory + "\u001B[0m");
            throw new StoreException("Remove Product", "There Is Not Enough Capacity on the Shelf");
        }

        //Remove the product in the basket and increment product on the shelf
        this.productMap.merge(productId, count, (a, b) -> a - b);
        inventory.setCount(inventory.getCount() + count);

        //if product count in the basket is 0 remove it from the basket completely
        tempCount = this.productMap.get(productId);
        if(tempCount == 0){
            productMap.remove(productId);
        }
    }

    /**
     * Remove all Products from the Customer's Basket
     * @throws StoreException
     */
    synchronized public void clearBasket() throws StoreException {

        //Removal of the products can't occur in the lambda function since we would get a
        //concurrent object modification exception
        Set<String> keys = this.productMap.keySet();
        List<Integer> values = new ArrayList<>(productMap.values());

        //Remove all the products from the Product Map
        int i = 0;
        for (String key : keys) {
            removeProduct(key, values.get(i));
            i++;
        }

        this.productMap.clear();

        //Clear Basket and remove Customer association
        this.customer.assignBasket(null);
        this.customer = null;
    }

    /**
     * Setter method for the Customer to establish a connection between Basket and the Store
     * @param store
     */
    public void setStore(Store store){
        this.store = store;
    }
    /**
     * Setter method for the Customer to establish a connection between Basket and the Customer
     * @param customer
     */
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    /**
     * Getter method for Store for a given Basket
     * @return
     */
    public Store getStore(){
        return this.store;
    }

    /**
     * Getter method for Customer for a given Basket
     * @return
     */
    public Customer getCustomer(){
        return this.customer;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id='" + id + '\'' +
                ", productMap=" + productMap +
                '}';
    }
}
