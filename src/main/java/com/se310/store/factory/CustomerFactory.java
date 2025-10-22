package com.se310.store.factory;

import com.se310.store.model.Customer;
import com.se310.store.model.CustomerType;

/**
 * Factory Pattern implementation for creating Customer objects
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class CustomerFactory {

    //TODO: Implement Customer Factory for creating guests and registered customers
    public static Customer createCustomer(String id, String firstName, String lastName, CustomerType type, String email, String accountAddress) {
        
        switch (type) {
            case guest:
                return new Customer(id, firstName, lastName, type, email, accountAddress) ;
            case registered:
                return new Customer(id, firstName, lastName, type, email, accountAddress) ;
            default:
                return new Customer(id, firstName, lastName, type, email, accountAddress) ;

        }


    }

}