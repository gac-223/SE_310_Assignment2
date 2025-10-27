package com.se310.store.observer;

import com.se310.store.model.StoreLocation;

/**
 * Concrete Observer that monitors for critical events and alerts
 *
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class AlertMonitor implements Observer {

    private static AlertMonitor instance ;


    public static AlertMonitor getInstance() {

        if (instance == null) {
            synchronized(AlertMonitor.class) {
                if (instance == null) {
                    instance = new AlertMonitor() ;
                }
            }
        }

        return instance ;
    }

    //TODO: Implement Alert Monitor that prints out critical events to the console
    public synchronized void update (String deviceId, String eventType, String message) {

        // print message if contains - "emergency", "critical", "error", "failure", or "alarm"
        if (message.contains("emergency") || message.contains("critical") || message.contains("error") || message.contains("failure") || message.contains("alarm")) {
            System.out.println("[AlertMonitor] Critical Event | Device: " + deviceId + " | Event Type: " + eventType + " | Message: " + message) ;
        }
        
    }
}