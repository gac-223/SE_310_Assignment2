package com.se310.store.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete Observer that logs device events to console
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class EventLogger implements Observer {

    //TODO: Implement Event Logger to log device events to console
    public synchronized void update(String deviceId, String eventType, String message) {
        System.out.println(message) ;
    }

}