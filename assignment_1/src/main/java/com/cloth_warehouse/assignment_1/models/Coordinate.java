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


public class Coordinate {
    double latitude;
    double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}