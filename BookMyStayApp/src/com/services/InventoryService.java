package com.services;
import java.util.HashMap;
import java.util.Map;

// Actor: Inventory Service
public class InventoryService {
    // Key Data Structures
    private Map<String, Integer> roomCounts; // Room type -> available count
    private Map<String, Double> roomPrices;  // Room type -> price per night

    public InventoryService() {
        this.roomCounts = new HashMap<>();
        this.roomPrices = new HashMap<>();
    }

    // Flow: Add room type -> Store in HashMap -> Update count/price -> Confirm
    public void addRoomType(String type, int initialCount, double price) {
        roomCounts.put(type, initialCount);
        roomPrices.put(type, price);
        System.out.println("Confirmed: Added room type '" + type + "' | Count: " + initialCount + " | Price: $" + price);
    }

    // Key Requirement: Support dynamic inventory updates
    public void updateInventory(String type, int countDelta) {
        if (roomCounts.containsKey(type)) {
            int currentCount = roomCounts.get(type);
            int newCount = currentCount + countDelta;
            
            if (newCount < 0) {
                System.out.println("Error: Cannot reduce inventory below 0 for " + type);
            } else {
                roomCounts.put(type, newCount);
                System.out.println("Update: Inventory changed for '" + type + "'. New count: " + newCount);
            }
        } else {
            System.out.println("Error: Room type '" + type + "' does not exist in inventory.");
        }
    }

    // Key Requirement: Provide real-time availability status (O(1) Access)
    public int getAvailableCount(String type) {
        return roomCounts.getOrDefault(type, 0);
    }

    public double getPrice(String type) {
        return roomPrices.getOrDefault(type, 0.0);
    }

    // Helper method to display the centralized inventory
    public void displayInventory() {
        System.out.println("\n--- Real-Time Room Inventory ---");
        for (String type : roomCounts.keySet()) {
            System.out.printf("Type: %-8s | Available: %-2d | Price: $%.2f%n", 
                              type, roomCounts.get(type), roomPrices.get(type));
        }
        System.out.println("--------------------------------\n");
    }
}