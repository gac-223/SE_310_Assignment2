package com.se310.store.observer;

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

    private AlertMonitor() {

    }

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
        
    }
}