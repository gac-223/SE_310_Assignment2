package com.se310.store.proxy;

import com.se310.store.model.Store;
import com.se310.store.model.StoreException;
import com.se310.store.singleton.StoreService;

/**
 * Proxy Pattern implementation for the StoreService
 * The Proxy Pattern provides a surrogate or placeholder for another object to control access to it
 * This implementation adds access control before delegating to the real StoreService
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreServiceProxy {

    // reference to StoreService
    private StoreService storeService ;
    String accessToken ;

    public StoreServiceProxy(StoreService storeService, String accessToken) {
        this.storeService = storeService ;

        this.accessToken = accessToken ;
    }

    private Boolean checkAuthorization(String token) {
        return accessToken.equals(token) ;
    }

    //TODO: Implement Proxy Pattern allowing command execution only with a valid token

}
