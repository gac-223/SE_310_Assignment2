package com.se310.store.observer;

import com.se310.store.model.StoreLocation;

/**
 * Concrete Observer that sends notifications to store management.
 * Implements Singleton pattern to ensure single notification instance per store.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreNotifier implements Observer {

    // StoreLocation
    private StoreLocation storeLocation ;

    public StoreNotifier(StoreLocation storeLocation) {
        this.storeLocation = storeLocation ;
    }

    //TODO: Implement Store Notifier that prints out device events to console
    public synchronized void update(String deviceId, String eventType, String message) {

        System.out.println("[StoreNotifier] " + this.storeLocation.getStoreId() + " | Device: " + deviceId + " | Notifaction: " + eventType + " - " + message) ;
    }

}