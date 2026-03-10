package com.services;
import java.util.HashMap;
import java.util.Map;

// Core Data Source (from UC1, extended for amenities)
public class InventoryService {
    private Map<String, Integer> roomCounts = new HashMap<>();
    private Map<String, Double> roomPrices = new HashMap<>();

    public void addRoomType(String type, int initialCount, double price) {
        roomCounts.put(type, initialCount);
        roomPrices.put(type, price);
        System.out.println("Inventory Updated: Added " + initialCount + " '" + type + "' room(s) at $" + price);
    }

    public int getAvailableCount(String type) {
        return roomCounts.getOrDefault(type, 0);
    }

    public void decrementInventory(String type) {
        int currentCount = getAvailableCount(type);
        if (currentCount > 0) {
            roomCounts.put(type, currentCount - 1);
        }
    }

    public Map<String, Integer> getRoomCounts() {
        return new HashMap<>(roomCounts);
    }
    
    public double getPrice(String type) {
        return roomPrices.getOrDefault(type, 0.0);
    }
}