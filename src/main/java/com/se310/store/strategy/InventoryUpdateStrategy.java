package com.se310.store.strategy;

import com.se310.store.model.Inventory;
import com.se310.store.model.StoreException;

// provides an abstract interface for the different update strategy implementations
public interface InventoryUpdateStrategy {
    
    public void updateInventory(Inventory inventory, int amount) throws StoreException ;

}
