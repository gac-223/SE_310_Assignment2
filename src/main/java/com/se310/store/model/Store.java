package com.se310.store.model;

import java.util.*;

/**
 * Store class implementation representing store of the Store Model Service
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Store {

    private String id;
    private String address;
    private String description;
    private final Map<String, Aisle> aislesMap;
    private final Map<String, Device> deviceMap;
    private final Map<String, Customer> customerMap;
    private final Map<String, Inventory> inventoryMap;
    private final Map<String, Basket> basketMap;

    /**
     * Constructor for the Store class
     * @param id
     * @param address
     * @param description
     */
    public Store(String id, String address, String description) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.aislesMap = new HashMap<>();
        this.deviceMap = new HashMap<>();
        this.customerMap = new HashMap<>();
        this.inventoryMap = new HashMap<>();
        this.basketMap = new HashMap<>();
    }

    /**
     * Getter method for Store id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for Store id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for Store address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for the Store address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for the Store description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for the Store description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Add aisle to the Store. If Aisle already exists in the store throw StoreException
     * @param aisleNumber
     * @param name
     * @param description
     * @param aisleLocation
     * @return
     * @throws StoreException
     */
    public Aisle addAisle(String aisleNumber, String name, String description, AisleLocation aisleLocation) throws StoreException {

        //Check to see if Aisle already exists
        Aisle aisle = new Aisle(aisleNumber, name, description, aisleLocation );
        if(this.aislesMap.putIfAbsent(aisleNumber,aisle) != null)
            throw new StoreException("Add Aisle", "Aisle Already Exists");

        return aisle;
    }

    /**
     * Get Aisle from the Store. If Aisle does not exist throw StoreException
     * @param aisleNumber
     * @return
     * @throws StoreException
     */
    public Aisle getAisle(String aisleNumber) throws StoreException {

        Aisle aisle = this.aislesMap.get(aisleNumber);
        //Check to see if Aisle exists in the Store
        if(aisle == null){
            throw new StoreException("Get Aisle", "Aisle Does Not Exist");
        }

        return this.aislesMap.get(aisleNumber);
    }

    /**
     * Method for keeping local reference of the Inventory in the Store.
     * If Inventory already exists in the Store throw StoreException
     * @param inventory
     */
    public void addInventory (Inventory inventory) throws StoreException {

        //Check to see if Inventory already exists in the Store
        if(this.inventoryMap.putIfAbsent(inventory.getId(), inventory) != null)
            throw new StoreException("Add Inventory", "Inventory Already Exists");

    }

    /**
     * Method for keeping local reference of the Customer in the Store.
     * If Customer already exists in the Store throw StoreException
     * @param customer
     * @throws StoreException
     */
    public void addCustomer(Customer customer) throws StoreException {

        //Check to see if Customer already exists in the Store
        if(this.customerMap.putIfAbsent(customer.getId(), customer) != null)
            throw new StoreException("Add Customer", "Customer Already Exists");
    }

    /**
     * Method for keeping local reference of the Device in the Store.
     * If Device already exists in the Store throw StoreException
     * @param device
     * @throws StoreException
     */
    public void addDevice(Device device) throws StoreException {

        //Check to see if Device already exists in the Store
        if(this.deviceMap.putIfAbsent(device.getId(), device) != null)
            throw new StoreException("Add Device", "Device Already Exists");

    }

    /**
     * Method for keeping local reference of the Basket in the Store.
     * If Basket already exists in the Store throw StoreException
     * @param basket
     * @throws StoreException
     */
    public void addBasket(Basket basket) throws StoreException {
        //Check to see if basket already exists in the Store
        if(this.basketMap.putIfAbsent(basket.getId(), basket) != null)
            throw new StoreException("Add Device", "Device Already Exists");

    }

    /**
     * Method getting Customer by Id
     * @param customerId
     * @return
     */
    public Customer getCustomer(String customerId){
        return this.customerMap.get(customerId);
    }

    /**
     * Method for removing Customer from the Store
     * @param customer
     */
    public void removeCustomer(Customer customer){

        if(this.customerMap.get(customer.getId()) == null){
            this.customerMap.remove(customer.getId());
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", aislesMap=" + aislesMap +
                ", deviceMap=" + deviceMap +
                ", customerMap=" + customerMap +
                ", inventoryMap=" + inventoryMap +
                ", basketMap=" + basketMap +
                '}';
    }
}
