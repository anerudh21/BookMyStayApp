package com.main;
import com.services.InventoryService;

// Actor: Hotel Admin (represented via the main method driving the app)
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BookMyStay App: Use Case 1 ===");
        
        InventoryService inventory = new InventoryService();

        // Key Requirement: Initialize room types (Single, Double, Suite)
        System.out.println("\n[Admin Action: Initializing Inventory]");
        inventory.addRoomType("Single", 10, 100.00);
        inventory.addRoomType("Double", 5, 150.00);
        inventory.addRoomType("Suite", 2, 300.00);

        // Check real-time status
        inventory.displayInventory();

        // Key Requirement: Support dynamic inventory updates
        System.out.println("[Admin Action: Dynamic Updates]");
        // E.g., A double room is taken offline for maintenance
        inventory.updateInventory("Double", -1); 
        // E.g., We added two new Single rooms to the property
        inventory.updateInventory("Single", 2);  

        // Check real-time status again to verify data consistency
        inventory.displayInventory();
        
        // Fast lookup demonstration
        System.out.println("[Checking O(1) Access]");
        System.out.println("Immediate Suite availability check: " + inventory.getAvailableCount("Suite") + " available.");
    }
}

