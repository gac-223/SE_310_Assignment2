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

    private static EventLogger instance ;

    private final DateTimeFormatter formatter ;


    private EventLogger() {
        formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME ;
    }

    // threadsafeDoubleChecked singleton
    public static EventLogger getInstance() {
        if (instance == null) {
            synchronized(EventLogger.class) {
                if (instance == null) {
                    instance = new EventLogger() ;
                }
            }
        }

        return instance ;
    }

    //TODO: Implement Event Logger to log device events to console
    public synchronized void update(String deviceId, String eventType, String message) {

        LocalDateTime dateTime = LocalDateTime.now() ;

        String formattedDateTime = formatter.format(dateTime) ;

        LocalDateTime parsedDate = LocalDateTime.parse(formattedDateTime, formatter) ;

        System.out.println(parsedDate) ;

        // can make prettier
        System.out.println("[EventLogger] " + formattedDateTime + " - Device: " + deviceId + " | Type: " + eventType + " | Message: " + message) ;

    }

}