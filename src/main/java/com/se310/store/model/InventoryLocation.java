package com.se310.store.model;

public class InventoryLocation extends StoreLocation {

    private String shelfId;

    public InventoryLocation(String storeId, String aisleId, String shelfId) {
        super(storeId, aisleId);
        this.shelfId = shelfId;
    }

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    @Override
    public String toString() {
        return "InventoryLocation{" +
                "storeLocation='" + super.toString() + '\'' +
                ", shelfId='" + shelfId + '\'' +
                '}';
    }
}
