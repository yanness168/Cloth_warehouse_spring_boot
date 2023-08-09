package com.cloth_warehouse.assignment_1.models;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.LinkedHashMap;


import com.cloth_warehouse.assignment_1.models.dto.DistributionCenterContext;
import com.cloth_warehouse.assignment_1.models.dto.ItemContext;

class Coordinate {
    double latitude;
    double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

public class Location {

    // Coordinates of Etobicoke Clothes Warehouse
    public static double WAREHOUSE_LATITUDE = 43.6205;
    public static double WAREHOUSE_LONGITUDE = 79.5132;

    public static List<DistributionCenterContext> findAllCentersWithFilteredItem(List<DistributionCenterContext> distributionCenters,
                                                       List<ItemContext> filteredItems) {

        List<DistributionCenterContext> centersWithItemId = new ArrayList<DistributionCenterContext>();

        // Get IDs of items
        List<Long> idItemsList = filteredItems.stream().map(ItemContext::getId).collect(Collectors.toList());

        // Get IDs of distribution centers that contain items with filtered items from idItemsList
        for (DistributionCenterContext distributionCenter: distributionCenters) {
            List<ItemContext> distributionCenterItems = distributionCenter.getItems();

            List<ItemContext> centerItemsContainingItemId = distributionCenterItems.stream()
                    .filter(item -> idItemsList.contains(item.getId()))
                    .collect(Collectors.toList());

                System.out.println(centerItemsContainingItemId);
                if (!centerItemsContainingItemId.isEmpty()) {
                    System.out.println("Center with Item: " + distributionCenter.getName());
                    centersWithItemId.add(distributionCenter);
                }
            }

        System.out.println(centersWithItemId);
        return centersWithItemId;
    }


    public static Coordinate findClosestCenterCoordinate(Map<Long, Coordinate> coordinatesByID, double targetLatitude, double targetLongitude) {
        if (coordinatesByID == null || coordinatesByID.values().isEmpty()) {
            throw new IllegalArgumentException("Coordinate list cannot be null or empty");
        }

        // Compare with first coordinate entry at first
        Map<Long, Coordinate>  map = coordinatesByID;
        Coordinate firstValue = null;

        for (Coordinate value : map.values()) {
            firstValue = value;
            break; // Only retrieve the first value
        }

        // Get closest coordinate
        Coordinate closestCoordinate = firstValue;
        double closestDistance = distanceBetweenCoordinates(targetLatitude, targetLongitude, closestCoordinate.latitude, closestCoordinate.longitude);

        for (Coordinate coordinate : map.values()) {
            double distance = distanceBetweenCoordinates(targetLatitude, targetLongitude, coordinate.latitude, coordinate.longitude);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestCoordinate = coordinate;
            }
        }

        // Get distribution center associated with closest coordinate
        double closestCenterId = 0;

        for (Long key : map.keySet()) {
            if (map.get(key).equals(closestCoordinate)) {
                closestCenterId = key;
            }
        }

        System.out.println("Closest Center: " +  closestCenterId + " Latitude " + closestCoordinate.latitude + ", Longitude " + closestCoordinate.longitude);
        return closestCoordinate;
    }

    public static Map<Long, Coordinate> mapCentersByCoordinatesAndID(List<DistributionCenterContext> distributionCenters)  {
        Map<Long, Coordinate> coordinateMap = new HashMap<>();

        for (DistributionCenterContext distributionCenter : distributionCenters) {
            coordinateMap.put(distributionCenter.getId(),
                    new Coordinate(distributionCenter.getLatitude(),
                            distributionCenter.getLongitude()));
        }

        return coordinateMap;
    }

    public static double distanceBetweenCoordinates(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}