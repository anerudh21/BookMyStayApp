package com.services;

import java.util.Map;

public // Actor: Search Service
class SearchService {
    private InventoryService inventory;

    public SearchService(InventoryService inventory) {
        this.inventory = inventory;
    }

    // Flow: Search request -> HashMap lookup -> Filter available rooms -> Display 
    public void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms & Amenities ---");
        Map<String, Integer> counts = inventory.getRoomCounts();
        boolean foundAvailable = false;

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String roomType = entry.getKey();
            int availableCount = entry.getValue();

            // Filter: Only show rooms that are currently available
            if (availableCount > 0) {
                foundAvailable = true;
                double price = inventory.getPrice(roomType);
                String amenities = inventory.getAmenities(roomType);
                System.out.printf("Type: %-8s | Price: $%-6.2f | Available: %-2d | Amenities: %s%n", 
                                  roomType, price, availableCount, amenities);
            }
        }

        if (!foundAvailable) {
            System.out.println("Sorry, no rooms are currently available.");
        }
        System.out.println("-----------------------------------");
    }

    // Key Requirement: Prevent booking unavailable rooms (Defensive check)
    public boolean canBook(String roomType, int requestedRooms) {
        int available = inventory.getAvailableCount(roomType);
        
        if (available >= requestedRooms) {
            System.out.println("Availability Validation: SUCCESS. " + requestedRooms + " '" + roomType + "' room(s) available.");
            return true;
        } else {
            System.out.println("Availability Validation: FAILED. Only " + available + " '" + roomType + "' room(s) left.");
            return false;
        }
    }
}