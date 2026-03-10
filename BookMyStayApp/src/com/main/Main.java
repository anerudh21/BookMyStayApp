package com.main;
import com.services.*;

//Actor: Guest (represented via the main method) [cite: 72]
public class Main {
 public static void main(String[] args) {
     System.out.println("=== BookMyStay App: Use Case 2 ===");

     // 1. Setup Inventory (Simulating backend state)
     InventoryService inventory = new InventoryService();
     inventory.setupRoom("Single", 5, 100.00, "1 Twin Bed, Free WiFi, AC");
     inventory.setupRoom("Double", 0, 150.00, "1 Queen Bed, Free WiFi, AC, TV"); // Intentionally 0 to test filtering
     inventory.setupRoom("Suite", 2, 300.00, "1 King Bed, Minibar, Balcony, Spa Access");

     // 2. Initialize Search Service
     SearchService searchService = new SearchService(inventory);

     // 3. Guest Action: Search available rooms
     System.out.println("\n[Guest Action: Searching for rooms]");
     // This will only display Single and Suite, as Double has 0 availability.
     searchService.displayAvailableRooms(); 

     // 4. Guest Action: Attempting to proceed with booking (Validation Check)
     System.out.println("\n[Guest Action: Checking availability for specific booking intent]");
     
     // Validating an available room
     System.out.print("Checking 1 Single room... ");
     searchService.canBook("Single", 1);

     // Validating an unavailable room (Defensive Check)
     System.out.print("Checking 1 Double room... ");
     searchService.canBook("Double", 1);
     
     // Validating exceeding capacity
     System.out.print("Checking 3 Suite rooms... ");
     searchService.canBook("Suite", 3);
 }
}
