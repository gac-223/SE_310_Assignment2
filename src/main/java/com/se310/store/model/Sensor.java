package com.se310.store.model;

/**
 * Sensor class implementation representing Sensor Device in the Store
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Sensor extends Device{

    //TODO: Implement Observer notification

    /**
     * Constructor for Sensor class
     * @param id
     * @param name
     * @param storeLocation
     * @param type
     */
    public Sensor(String id, String name, StoreLocation storeLocation, String type) {
        super(id, name, storeLocation, type);
    }

    @Override
    /**
     * Sensor specific event processing
     * Notifies observers when an event is processed
     */
    public void processEvent(String event) {
        System.out.println("<<< " + "Processing Event : " + event);

        this.notifyObservers(event) ;
    }
}