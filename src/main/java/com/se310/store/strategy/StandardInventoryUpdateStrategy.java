package com.se310.store.strategy;

import com.se310.store.model.Inventory;
import com.se310.store.model.Store;
import com.se310.store.model.StoreException;

/**
 * Standard implementation of the Inventory
 * This strategy performs a standard inventory update, checking bounds before updating
 * Part of the Strategy Pattern implementation
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StandardInventoryUpdateStrategy implements InventoryUpdateStrategy {

    //TODO: Implement Strategy Pattern making sure inventory stays in the acceptable bounds for standard products

    public void updateInventory(Inventory inventory, int amount) throws StoreException {


        if (amount < 0) {
            throw new StoreException("Update Inventory", "Add Amount Cannot Be Less Than 0") ;
        }

        if ((inventory.getCount() + amount) > inventory.getCapacity()) {
            throw new StoreException("Update Inventory", "Inventory is Larger Than Shelf Capacity") ;
        }

        inventory.setCapacity(inventory.getCount() + amount);

    }
}