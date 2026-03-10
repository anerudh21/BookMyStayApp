package com.services;

import java.util.Map;

public class SearchService {
    private InventoryService inventory;

    public SearchService(InventoryService inventory) {
        this.inventory = inventory;
    }

    public void displayAvailableRooms() {
        System.out.println("\n--- Live Availability Search ---");
        boolean found = false;
        for (Map.Entry<String, Integer> entry : inventory.getRoomCounts().entrySet()) {
            if (entry.getValue() > 0) {
                found = true;
                System.out.printf("Type: %-8s | Available: %-2d | Price: $%.2f%n", 
                                  entry.getKey(), entry.getValue(), inventory.getPrice(entry.getKey()));
            }
        }
        if (!found) System.out.println("No rooms currently available.");
        System.out.println("--------------------------------");
    }
}
