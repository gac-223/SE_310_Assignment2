package com.se310.store.observer;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete Observer that tracks statistics about device events
 *
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class DeviceStatistics implements Observer {

    private static DeviceStatistics instance ;

    // a hashmap to store deviceEventCounts
    private final HashMap<String, Integer> deviceEventCounts ;

    // a hashmap to store deviceCommandCounts
    private final HashMap<String, Integer> deviceCommandCounts ;

    private DeviceStatistics() {
        deviceEventCounts = new HashMap<>() ;
        deviceCommandCounts = new HashMap<>() ;
    }

    public static DeviceStatistics getInstance() {
        if (instance == null) {

            synchronized(DeviceStatistics.class) {
                if (instance == null) {
                    instance = new DeviceStatistics() ;
                }
            }
        }
        return instance ;
    }



    //TODO: Implement Device Statistics stores and prints out event and command counts

    public synchronized void update(String deviceId, String eventType, String message) {

        switch (eventType) {
            case "Command":

                // if deviceCommandCounts contains deviceId, then increase count by 1
                // else set to 1
                deviceCommandCounts.put(deviceId, deviceCommandCounts.getOrDefault(deviceId, 0) + 1) ;
                break ;


            case "Event":

                deviceEventCounts.put(deviceId, deviceEventCounts.getOrDefault(deviceId, 0) + 1) ;
                break ;
        }

        System.out.println("[DeviceStatistics] Device: " + deviceId + " | Num Events: " + this.deviceEventCounts.getOrDefault(deviceId, 0) + " | Num Commands: " + this.deviceCommandCounts.getOrDefault(deviceId, 0) + " | Message: " + message) ;
    }


}