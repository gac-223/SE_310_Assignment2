package com.se310.store.observer;

/**
 * Observable interface for the Observer Pattern
 * Devices implement this interface to allow observers to subscribe to their state changes
 *
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public interface Observable {

    //TODO: Define Observer registration

    // attaches an observer to the collection of observers
    void attach(Observer observer) ;

    //TODO: Define Observer removal

    // detaches an observer from the collection of observers
    void detach(Observer observer) ;

    //TODO: Define Observer notification

    // notifies all observers in collection of a state change
    // calls the observer.update(String deviceId, String eventType, String message) method for each observer
    void notifyObservers() ;

}