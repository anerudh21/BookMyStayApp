package com.services;

import java.util.HashMap;
import java.util.Map;

// Core Data Source (from UC1, extended for amenities)
public class InventoryService {
    private Map<String, Integer> roomCounts;
    private Map<String, Double> roomPrices;
    private Map<String, String> roomAmenities; // Added to satisfy "Show pricing and amenities"

    public InventoryService() {
        this.roomCounts = new HashMap<>();
        this.roomPrices = new HashMap<>();
        this.roomAmenities = new HashMap<>();
    }

    // Admin function to setup inventory
    public void setupRoom(String type, int count, double price, String amenities) {
        roomCounts.put(type, count);
        roomPrices.put(type, price);
        roomAmenities.put(type, amenities);
    }

    // --- Read-Only Access Methods for Search Service ---
    
    // Returns a defensive copy to prevent external mutation
    public Map<String, Integer> getRoomCounts() {
        return new HashMap<>(roomCounts); 
    }

    public double getPrice(String type) {
        return roomPrices.getOrDefault(type, 0.0);
    }

    public String getAmenities(String type) {
        return roomAmenities.getOrDefault(type, "Standard Amenities");
    }

    public int getAvailableCount(String type) {
        return roomCounts.getOrDefault(type, 0);
    }
}