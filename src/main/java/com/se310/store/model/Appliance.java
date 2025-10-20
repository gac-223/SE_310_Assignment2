package com.se310.store.model;
/**
 * Appliance class implementation representing Appliance Device in the Store
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class Appliance extends Device{

    //TODO: Implement Observer notification related to event processing
    //TODO: Implement Observer notification related to command processing

    /**
     * Constructor for Appliance class
     * @param id
     * @param name
     * @param storeLocation
     * @param type
     */
    public Appliance(String id, String name, StoreLocation storeLocation, String type) {
        super(id, name, storeLocation, type);
    }

    /**
     * Appliance specific event processing
     * Notifies observers when an event is processed
     */
    @Override
    public void processEvent(String event) {
        System.out.println("Processing Event : " + event);


        this.notifyObservers() ;
    }

    /**
     * This is a placeholder for the processing commands
     * Notifies observers when a command is issued
     * @param command
     */
    public void processCommand(String command){
        System.out.println("<<< " + "Processing Command : " + command);


        this.notifyObservers() ;
    }
}
