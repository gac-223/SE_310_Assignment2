package com.se310.store.model;

import com.se310.store.observer.Observable;
import com.se310.store.observer.Observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects ;

/**
 * Abstract class representing Device in the Store
 * Implements Observable to allow observers to monitor device state changes
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public abstract class Device implements Observable {

    //TODO: Implement Observable interface
    //TODO: Implement Observer storage
    //TODO: Implement Observer registration
    //TODO: Implement Observer removal
    //TODO: Implement Observer notification

    private String id;
    private String name;
    private StoreLocation storeLocation;
    private String type;


    protected final List<Observer> observers;



    // ensure not null
    // ensure no duplicates
    public synchronized void attach(Observer observer) {

        Objects.requireNonNull(observer, "Observer cannot be null") ;

        if (!(observers.contains(observer))) {
            observers.add(observer) ;
        }


    }

    // implementation of removal
    public synchronized void detach(Observer observer) {

        observers.remove(observer) ;

    }


    // loop thru list of observers
    // use a try catch on update, this ensures that if one observer throws an exception, it won't effect other observers
        // other observers will still get notified
    public synchronized void notifyObservers(String eventType, String eventMessage) {


        // call update on each observer
        for (Observer observer : observers) {

            try {

                observer.update(this.id, eventType, eventMessage) ;

            }  catch (Exception e) {
                System.out.println("Notify Observer | Device: " + this.id + " failed to notify Observer " + observer) ;
            }
        }

    }

    /**
     * Constructor for the Device class
     * @param id
     * @param name
     * @param storeLocation
     * @param type
     */
    public Device(String id, String name, StoreLocation storeLocation, String type) {
        this.id = id;
        this.name = name;
        this.storeLocation = storeLocation;
        this.type = type;
        this.observers = new ArrayList<>();
    }

    /**
     * Getter method for the Device id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for the Device id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for the Device name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the Device name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the Store location
     * @return
     */
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    /**
     * Setter method for the Store location
     * @param storeLocation
     */
    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    /**
     * Getter method for the Device type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for the Device type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Abstract method that gets implemented by Sensor or Appliance devices
     * @param event
     */
    public abstract void processEvent(String event);

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", storeLocation=" + storeLocation +
                ", type='" + type + '\'' +
                '}';
    }
}
