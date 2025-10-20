package com.se310.store.model;

/**
 * Store Location class implementation consisting of Store id and the Aisle id
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreLocation {

    private String storeId;
    private String aisleId;

    /**
     * Store Location Constructor
     * @param storeId
     * @param aisleId
     */
    public StoreLocation(String storeId, String aisleId) {
        this.storeId = storeId;
        this.aisleId = aisleId;
    }

    /**
     * Getter method for Store Location Store id
     * @return
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * Setter method for Store Location Store id
     * @param storeId
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * Getter method for Store Location Aisle id
     * @return
     */
    public String getAisleId() {
        return aisleId;
    }

    /**
     * Setter method for Store Location Store id
     * @param aisleId
     */
    public void setAisleId(String aisleId) {
        this.aisleId = aisleId;
    }

    @Override
    public String toString() {
        return "StoreLocation{" +
                "storeId='" + storeId + '\'' +
                ", aisleId='" + aisleId + '\'' +
                '}';
    }
}
