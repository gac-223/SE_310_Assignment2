package com.se310.store.model;

import com.se310.store.strategy.FlexibleInventoryUpdateStrategy;
import com.se310.store.strategy.InventoryUpdateStrategy;
import com.se310.store.strategy.StandardInventoryUpdateStrategy;

/**
 * Inventory class implementation representing inventory on the shelf of the store
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Inventory {

    private String id;
    private InventoryLocation inventoryLocation;
    private int capacity;
    private int count;
    private String productId;
    private InventoryType type;

    // each inventory has its own updateStrategy
    private InventoryUpdateStrategy updateStrategy ;

    /**
     * Constructor for the Inventory class
     * @param id
     * @param inventoryLocation
     * @param capacity
     * @param count
     * @param productId
     * @param type
     */
    public Inventory(String id, InventoryLocation inventoryLocation, int capacity, int count, String productId, InventoryType type) {
        this.id = id;
        this.inventoryLocation = inventoryLocation;
        this.capacity = capacity;
        this.count = count;
        this.productId = productId;
        this.type = type;

        

    }

    /**
     * Getter method for Inventory id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for Inventory id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for Inventory Location on the shelf of the store
     * @return
     */
    public InventoryLocation getInventoryLocation() {
        return inventoryLocation;
    }

    /**
     * Setter method for Inventory Location on the shelf of the store
     * @param inventoryLocation
     */
    public void setInventoryLocation(InventoryLocation inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    /**
     * Getter method for Inventory capacity on the shelf of the store
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Setter method for Inventory capacity on the shelf of the store
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter method for Inventory count on the shelf of the store
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter method for Inventory count on the shelf of the store
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Getter method for the Product associated with the Inventory item
     * @return
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Setter method for the Product associated with the Inventory item
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Method for updating (incrementing or decrementing) Inventory on the shelf of the store.
     * Throws StoreException if count does not stay within allowable bounds.
     * Method is synchronized to avoid a race condition
     * @param count
     * @throws StoreException
     */
    synchronized public void updateInventory(int count) throws StoreException {

        if (this.updateStrategy == null) {
            throw new StoreException("Update Inventory", "This Inventory Does Not Have an Update Strategy") ;
        }

        this.updateStrategy.updateInventory(this, count) ;
    }

    /**
     * Getter method for Inventory type
     * @return The inventory type enum value
     */
    public InventoryType getType() {
        return type;
    }

    /**
     * Sets the type of the inventory item.
     *
     * @param type The inventory type to set. Accepts an {@code InventoryType} enumeration value.
     */
    public void setType(InventoryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id='" + id + '\'' +
                ", inventoryLocation=" + inventoryLocation +
                ", capacity=" + capacity +
                ", count=" + count +
                ", productId='" + productId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    // must set the update strategy at runtime
    public void setInventoryUpdateStrategy(InventoryUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy ;
    }

}
