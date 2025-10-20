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

    //TODO: Implement Alert Monitor that prints out critical events to the console
    public synchronized void update (String deviceId, String eventType, String message) {
        switch (eventType) {
            case "emergency":
            case "critical":
            case "error":
            case "failure":
            case "alarm":
                System.out.println("Device: " + deviceId + "\tMessage: " + message) ;
        }
    }
}