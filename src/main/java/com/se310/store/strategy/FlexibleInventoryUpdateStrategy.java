package com.se310.store.strategy;

import com.se310.store.model.Inventory;
import com.se310.store.model.StoreException;

/**
 * Flexible implementation of the Inventory
 * This strategy allows temporary over-capacity for inventory updates
 * Part of the Strategy Pattern implementation
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class FlexibleInventoryUpdateStrategy implements InventoryUpdateStrategy {

    // private int overcapacity_percent ;

    // public FlexibleInventoryUpdateStrategy(int overcapacity_percent) {
    //     this.overcapacity_percent = overcapacity_percent ;
    // }

    //TODO: Implement Strategy Pattern allowing 20% overcapacity for flexible inventory

    public void updateInventory(Inventory inventory, int amount) throws StoreException {

        

        double overcap_amount = inventory.getCapacity() * 0.2 ;

        if (amount < 0) {
            throw new StoreException("Update Inventory", "Add Amount Cannot Be Less Than 0") ;
        }
        if ((inventory.getCount() + amount) > (inventory.getCapacity() + overcap_amount)){
            throw new StoreException("Update Inventory", "Inventory is Larger Than Shelf Capacity") ;
        }

        inventory.setCount(inventory.getCount() + amount) ;

    }
}