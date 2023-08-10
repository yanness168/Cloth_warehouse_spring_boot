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
import com.cloth_warehouse.assignment_1.models.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Location {
    // Coordinates of Etobicoke Clothes Warehouse
    public static double WAREHOUSE_LATITUDE = 43.6205;
    public static double WAREHOUSE_LONGITUDE = 79.5132;

    public List<DistributionCenterContext> distributionCenters;
    public List<ItemContext> filteredItems;
    public Long closestCenterId;

    public List<DistributionCenterContext> findAllCentersWithFilteredItem() {

        List<DistributionCenterContext> centersWithItemId = new ArrayList<DistributionCenterContext>();

        // Get IDs of items
        List<Long> idItemsList = filteredItems.stream().map(ItemContext::getId).collect(Collectors.toList());

        // Get IDs of distribution centers that contain items with filtered items from idItemsList
        for (DistributionCenterContext distributionCenter: distributionCenters) {
            List<ItemContext> distributionCenterItems = distributionCenter.getItems();

            List<ItemContext> centerItemsContainingItemId = distributionCenterItems.stream()
                    .filter(item -> idItemsList.contains(item.getId()))
                    .collect(Collectors.toList());

                if (!centerItemsContainingItemId.isEmpty()) {
                    System.out.println("Center with Item: " + distributionCenter.getName());
                    System.out.println(centerItemsContainingItemId);
                    centersWithItemId.add(distributionCenter);
                }
            }

        return centersWithItemId;
    }

    public Map<Long, Coordinate> mapCentersByCoordinatesAndID()  {
        Map<Long, Coordinate> coordinateMap = new HashMap<>();

        for (DistributionCenterContext distributionCenter : this.distributionCenters) {
            coordinateMap.put(distributionCenter.getId(),
                    new Coordinate(distributionCenter.getLatitude(),
                            distributionCenter.getLongitude()));
        }

        return coordinateMap;
    }

    public Coordinate findClosestCenterByCoordinate(double targetLatitude,
                                                                      double targetLongitude) {

        Map<Long, Coordinate> coordinateMap = mapCentersByCoordinatesAndID();
        if (coordinateMap == null || coordinateMap.values().isEmpty()) {
            throw new IllegalArgumentException("Coordinate list cannot be null or empty");
        }

        // Compare with first coordinate entry at first
        Coordinate firstValue = null;

        for (Coordinate value : coordinateMap.values()) {
            firstValue = value;
            break; // Only retrieve the first value
        }

        // Get closest coordinate
        Coordinate closestCoordinate = firstValue;
        double closestDistance = distanceBetweenCoordinates(targetLatitude, targetLongitude, closestCoordinate.latitude, closestCoordinate.longitude);

        for (Coordinate coordinate : coordinateMap.values()) {
            double distance = distanceBetweenCoordinates(targetLatitude, targetLongitude, coordinate.latitude, coordinate.longitude);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestCoordinate = coordinate;
            }
        }

        // Get distribution center associated with closest coordinate
        Long closestCenterId = 0L;

        for (Long key : coordinateMap.keySet()) {
            if (coordinateMap.get(key).equals(closestCoordinate)) {
                closestCenterId = key;
            }
        }

        this.closestCenterId = closestCenterId;
        return closestCoordinate;
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