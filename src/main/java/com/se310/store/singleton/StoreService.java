package com.se310.store.singleton;

import java.util.*;
import java.util.stream.Collectors;

import com.se310.store.model.*;
import com.se310.store.observer.DeviceStatistics;
import com.se310.store.observer.EventLogger;
import com.se310.store.observer.StoreNotifier;

// implement an interface for StoreService And StoreServiceProxy to implement
    // StoreServiceProxy controls access to storeService singleton

/**
 * This is the main service of the system implementing ommand API for processing CLI commands and
 * rvice API for processing Store events
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since 2025-09-25
 **/
public class StoreService {

    //TODO: Implement Thread Safe Double-Checked Locking Singleton Pattern
    private static StoreService instance ;

    private StoreService() {

    }

    public static StoreService getInstance() {
        if (instance == null) {
            synchronized(StoreService.class) {
                if (instance == null) {
                    instance = new StoreService() ;
                }
            }
        }

        return instance ;
    }

    private static final Map<String, Store> storeMap;
    private static final Map<String, Customer> customerMap;
    private static final Map<String, Product> productMap;
    private static final Map<String, Inventory> inventoryMap;
    private static final Map<String, Basket> basketMap;
    private static final Map<String, Device> deviceMap;


    // Initialize maps
    static {
        storeMap = new HashMap<>();
        customerMap = new HashMap<>();
        productMap = new HashMap<>();
        inventoryMap = new HashMap<>();
        basketMap = new HashMap<>();
        deviceMap = new HashMap<>();
    }


    public Store provisionStore(String storeId, String name, String address, String token)
            throws StoreException {

        Store store = new Store(storeId, name, address);

        //If Store already exists throw and exception
        if (storeMap.putIfAbsent(storeId, store) != null) {
            throw new StoreException("Provision Store", "Store Already Exists");
        }
        return store;
    }

    public Store showStore(String storeId, String token) throws StoreException {

        //If Store does not exist throw and exception
        Store store = storeMap.get(storeId);
        if(store == null)
            throw new StoreException("Show Store", "Store Does Not Exist");

        return store;
    }

    public Aisle provisionAisle(String storeId, String aisleNumber, String name, String description,
                                AisleLocation location, String token) throws StoreException {

        Store store = storeMap.get(storeId);
        Aisle aisle;

        //Check to see if Store already exists;
        if(store == null){
            throw new StoreException("Provision Aisle", "Store Does Not Exist");
        } else {
            aisle = store.addAisle(aisleNumber, name, description, location);
        }

        return aisle;
    }

    public Aisle showAisle(String storeId, String aisleNumber, String token) throws StoreException {
        Store store = storeMap.get(storeId);
        Aisle aisle;
        //Check to see if Store exists
        if(store == null){
            throw new StoreException("Show Aisle", "Store Does Not Exist");
        } else {
            //Check to see if Aisle already exists
            aisle = store.getAisle(aisleNumber);
            if (aisle == null) {
                throw new StoreException("Show Aisle", "Aisle Does Not Exist");
            }
        }
        return aisle;
    }

    public Shelf provisionShelf(String storeId, String aisleNumber, String shelfId, String name,
                                ShelfLevel level, String description, Temperature temperature, String token) throws StoreException {

        Store store = storeMap.get(storeId);
        Shelf shelf;

        //Check to see if Store exists
        if(store == null){
            throw new StoreException("Provision Shelf", "Store Does Not Exist");
        } else {
            Aisle aisle = store.getAisle(aisleNumber);
            //Check to see if Aisle exists
            if (aisle == null){
                throw new StoreException("Provision Shelf", "Aisle Does Not Exist");
            } else {
                shelf = aisle.getShelf(shelfId);
                //Check to see if Shelf exists
                if(shelf != null){
                    throw new StoreException("Provision Shelf", "Shelf Already Exists");
                }

                //Add Shelf to the Aisle
                shelf = aisle.addShelf(shelfId, name, level, description, temperature);
            }
        }
        return shelf;
    }

    public Shelf showShelf(String storeId, String aisleNumber, String shelfId, String token) throws StoreException {
        Store store = storeMap.get(storeId);
        Shelf shelf;

        //Check to see if Store exists
        if(store == null){
            throw new StoreException("Show Shelf", "Store Does Not Exist");
        } else {
            //Check to see if Aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null){
                throw new StoreException("Show Shelf", "Aisle Does Not Exist");
            } else {
                //Check to see if Shelf exists
                shelf = aisle.getShelf(shelfId);
                if(shelf == null){
                    throw new StoreException("Show Shelf", "Shelf Does Not Exist");
                }
            }
        }
        return shelf;
    }

    public Inventory provisionInventory(String inventoryId, String storeId, String aisleNumber, String shelfId,
                                        int capacity, int count, String productId, InventoryType type, String token) throws StoreException {

        Store store = storeMap.get(storeId);
        Product product = productMap.get(productId);
        Inventory inventory;

        //Check to see if Store exists
        if(store == null){
            throw new StoreException("Provision Inventory", "Store Does Not Exist");
        } else {
            //Check to see if Aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null){
                throw new StoreException("Provision Inventory", "Aisle Does Not Exist");
            } else {
                //Check to see if Shelf exists
                Shelf shelf = aisle.getShelf(shelfId);
                if(shelf == null){
                    throw new StoreException("Provision Inventory", "Shelf Does Not Exist");
                } else if(product == null){
                    //Check to see if Product exists
                    throw new StoreException("Provision Inventory", "Product Does Not Exist");
                } else if(!shelf.getTemperature().equals(product.getTemperature())){
                    //Make sure that Product Temperature and Shelf Temperature are consistent
                    throw new StoreException("Provision Inventory", "Product and Shelf Temperature " +
                            "Is Not Consistent");
                }

                //Add Inventory to the Shelf
                inventory = shelf.addInventory(inventoryId, storeId, aisleNumber, shelfId,
                        capacity, count, productId, type);

                //Add Inventory to the global Inventory Map
                inventoryMap.put(inventoryId, inventory);

                //Add Inventory to the Store
                store.addInventory(inventory);

            }
        }

        return inventory;
    }

    public Inventory showInventory(String inventoryId, String token) throws StoreException {

        Inventory inventory = inventoryMap.get(inventoryId);
        //Check to see if Inventory exists
        if (inventory == null)
            throw new StoreException("Show Inventory", "Inventory Does Not Exist");
        return inventory;
    }

    public Inventory updateInventory(String inventoryId, int count, String token) throws StoreException {
        Inventory inventory = inventoryMap.get(inventoryId);
        //Check to see if Inventory exists
        if (inventory == null)
            throw new StoreException("Update Inventory", "Inventory Does Not Exist");

        //Update Inventory count
        inventory.updateInventory(count);

        return inventory;
    }

    public Product provisionProduct(String productId, String name, String description, String size, String category,
                                    double price, Temperature temperature, String token) throws StoreException {
        Product product = new Product(productId, name, description, size, category, price, temperature);

        //Check to see if Product already exists
        if (productMap.putIfAbsent(productId, product) != null)
            throw new StoreException("Provision Product", "Product Already Exists");

        return product;
    }

    public Product showProduct(String productId, String token) throws StoreException {
        Product product = productMap.get(productId);
        //Check to see if Product exists
        if (product == null)
            throw new StoreException("Show Product", "Product Does Not Exist");
        return product;
    }


    public Customer provisionCustomer(String customerId, String firstName, String lastName,
                                      CustomerType type, String email, String address, String token)
            throws StoreException {

        Customer customer = new Customer(customerId, firstName, lastName, type, email, address);
        //Check to see if the Customer already exists
        if(customerMap.putIfAbsent(customerId, customer) != null)
            throw new StoreException("Provision Customer", "Customer Already Exists");

        return customer;
    }

    public Customer updateCustomer(String customerId, String storeId, String aisleNumber, String token)
            throws StoreException {
        Store store = storeMap.get(storeId);
        Customer customer;

        //Check to see if the Store exists
        if(store == null){
            throw new StoreException("Update Customer", "Store Does Not Exist");
        } else {
            //Check to see if Aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null){
                throw new StoreException("Update Customer", "Aisle Does Not Exist");
            } else {
                //Check to see if Customer exists
                customer = customerMap.get(customerId);
                if(customer == null){
                    throw new StoreException("Update Customer", "Customer Does Not Exist");
                }
            }
        }

        //Check to see if Customer changing Stores
        if(customer.getStoreLocation() != null && !customer.getStoreLocation().getStoreId().equals(storeId)){
            //Check to see if Customer already exists in other Stores
            Map<Store, Customer> customerStores  = storeMap.entrySet()
                    .stream()
                    .filter(tempStore -> (tempStore.getValue().getCustomer(customerId) != null && tempStore.getValue().getCustomer(customerId).getId().equals(customerId)))
                    .collect(Collectors.toMap(Map.Entry::getValue, tempStore -> tempStore.getValue().
                            getCustomer(customerId)));

            //If Customer exist in other stores remove him/her
            customerStores.forEach((key, value) -> key.removeCustomer(customer));

            //Before Customer can change the Store he/she must clear the Basket
            if(customer.getBasket() != null)
                customer.getBasket().clearBasket();

            //If the Customer moves to a different Store clear out the basket and the time seen
            customer.assignBasket(null);
            customer.setLastSeen(null);

            //Add Customer to another store
            store.addCustomer(customer);
        } else {

            customer.setStoreLocation(new StoreLocation(storeId, aisleNumber));
            customer.setLastSeen(new Date(System.currentTimeMillis()));
        }

        return customer;
    }

    public Customer showCustomer(String customerId, String token) throws StoreException {

        //Check to see if the Customer exists
        Customer customer = customerMap.get(customerId);
        if(customer == null)
            throw new StoreException("Show Customer", "Customer Does Not Exist");

        return customer;
    }

    public Basket provisionBasket(String basketId, String token) throws StoreException {

        Basket basket = new Basket(basketId);
        //Check if Basket already exists
        if(basketMap.putIfAbsent(basketId, basket) != null)
            throw new StoreException("Provision Basket", "Basket Already Exists");

        return basket;
    }

    public Basket assignCustomerBasket(String customerId, String basketId, String token) throws StoreException {

        Customer customer = customerMap.get(customerId);
        Basket basket = basketMap.get(basketId);

        //Check to see Customer and the Basket already exist
        if(customer == null){
            throw new StoreException("Assign Customer Basket", "Customer Does Not Exist");
        } else {
            if (basket == null){
                throw new StoreException("Assign Customer Basket", "Basket Does Not Exist");
            }
        }

        //Assign Basket to the Customer
        customer.assignBasket(basket);
        //Keep the global copy of all the baskets
        basketMap.put(basketId, basket);

        Store store = storeMap.get(customerMap.get(customerId).getStoreLocation().getStoreId());

        //Associate basket with the customer
        basket.setCustomer(customer);
        //Create bidirectional association between Store and the Basket
        basket.setStore(store);
        store.addBasket(basket);

        return basket;
    }

    public Basket getCustomerBasket(String customerId, String token) throws StoreException {
        Customer customer = customerMap.get(customerId);
        Basket basket;

        //Check if Customer exists
        if(customer == null){
            throw new StoreException("Get Customer Basket", "Customer Does Not Exist");
        } else {
            basket = customer.getBasket();
            //Check to see if Customer has been assigned the Basket
            if (basket == null) {
                throw new StoreException("Get Customer Basket", "Customer Does Not Have a Basket");
            }
        }
        return basket;
    }

    public Basket addBasketProduct(String basketId, String productId, int count, String token)
            throws StoreException {
        Basket basket = basketMap.get(basketId);
        Product product = productMap.get(productId);

        //Check to see if basket already exists product we are trying to add to the basket
        //exists as well and basket has been assigned to the customer
        if(basket == null){
            throw new StoreException("Add Basket Product", "Basket Does Not Exist");
        } else if(product == null){
            throw new StoreException("Add Basket Product", "Product Does Not Exist");
        } else if(basket.getCustomer() == null){
            throw new StoreException("Add Basket Product", "Basket Has Not Being Assigned");
        }
        //Add a product to the basket
        basket.addProduct(productId, count);

        return basket;
    }

    public Basket removeBasketProduct(String basketId, String productId, int count, String token) throws StoreException {
        Basket basket = basketMap.get(basketId);
        Product product = productMap.get(productId);

        //Check to see if basket already exists product we are trying to add to the basket
        //exists as well and basket has been assigned to the customer
        if(basket == null){
            throw new StoreException("Remove Basket Product", "Basket Does Not Exist");
        } else if(product == null){
            throw new StoreException("Remove Basket Product", "Product Does Not Exist");
        } else if(basket.getCustomer() == null){
            throw new StoreException("Remove Basket Product", "Basket Has Not Being Assigned");
        }
        //Remove product from the basket
        basket.removeProduct(productId, count);

        return basket;
    }

    public Basket clearBasket(String basketId, String token) throws StoreException {

        Basket basket = basketMap.get(basketId);

        //Check to see if basket already exists and basket has been assigned to the customer
        if(basket == null){
            throw new StoreException("Clear Basket", "Basket Does Not Exist");
        } else if(basket.getCustomer() == null){
            throw new StoreException("Clear Basket", "Basket Has Not Being Assigned");
        }
        basket.clearBasket();

        return basket;
    }

    public Basket showBasket(String basketId, String token) throws StoreException {
        Basket basket = basketMap.get(basketId);

        //Check to see if basket already exists and basket has been assigned to the customer
        if(basket == null){
            throw new StoreException("Show Basket Product", "Basket Does Not Exist");
        } else if(basket.getCustomer() == null){
            throw new StoreException("Show Basket Product", "Basket Has Not Being Assigned");
        }

        return basket;
    }

    // within provisionDevice, do we want to add concrete observers to each and every device?

    public Device provisionDevice(String deviceId, String name, String deviceType, String storeId,
                                  String aisleNumber, String token) throws StoreException {

        Store store = storeMap.get(storeId);
        Device device;
        StoreLocation storeLocation;

        //Check to see if store exists
        if(store == null){
            throw new StoreException("Provision Device", "Store Does Not Exist");
        } else {

            //Check to see if aisle exists
            Aisle aisle = store.getAisle(aisleNumber);
            if (aisle == null){
                throw new StoreException("Provision Device", "Aisle Does Not Exist");
            } else {
                storeLocation = new StoreLocation(storeId, aisleNumber);

                //Check to see if device already exists
                device = deviceMap.get(deviceId);
                if(device != null){
                    throw new StoreException("Provision Device", "Device Already Exists");
                }

                //Determine wha type of device we are trying to add
                for (SensorType sensor : SensorType.values()) {
                    if (sensor.name().equals(deviceType)){
                        device = new Sensor (deviceId, name, storeLocation, deviceType);
                    }
                }
                for (ApplianceType appliance : ApplianceType.values()) {
                    if (appliance.name().equals(deviceType)){
                        device = new Appliance(deviceId, name, storeLocation, deviceType);
                    }
                }

                //Add device to the global map
                deviceMap.put(deviceId,device);
                //Add device to the local store
                store.addDevice(device);

                // do we add the observers within here?
                device.attach(new StoreNotifier(storeLocation)) ;
                device.attach(EventLogger.getInstance()) ;
                device.attach(DeviceStatistics.getInstance()) ;

            }
        }
        return device;
    }

    public Device showDevice(String deviceId, String token) throws StoreException {
        Device device = deviceMap.get(deviceId);

        //Check to see if device exists
        if(device == null)
            throw new StoreException("Show Device", "Device Does Not Exist");

        return device;
    }

    public void raiseEvent(String deviceId, String event, String token) throws StoreException {
        Device device = deviceMap.get(deviceId);

        //Check to see if a device exists
        if(device == null){
            throw new StoreException("Raise Event", "Device Does Not Exist");
        }
        device.processEvent(event);

    }

    public void issueCommand(String deviceId, String command, String token) throws StoreException {

        Appliance appliance = (Appliance) deviceMap.get(deviceId);

        //Check to see if the appliance exists
        if(appliance == null){
            throw new StoreException("Issue Command", "Device Does Not Exist");
        }
        appliance.processCommand(command);
    }
}
