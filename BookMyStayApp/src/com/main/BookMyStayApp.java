package com.main;
import java.util.Map;

import com.reservation.*;
import com.services.*;

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("=== Starting BookMyStay Master App ===\n");

        // 1. Initialize all sub-systems
        InventoryService inventory = new InventoryService();
        SearchService search = new SearchService(inventory);
        ReportingService reporting = new ReportingService();
        ServiceManagementModule addons = new ServiceManagementModule();
        BookingService bookingManager = new BookingService(inventory, reporting);

        // 2. Setup Inventory (UC1)
        inventory.addRoomType("Single", 2, 100.0);
        inventory.addRoomType("Suite", 1, 300.0);

        // 3. Guest Search (UC2)
        search.displayAvailableRooms();

        // 4. High-Traffic Booking Requests (UC3) - Only 2 Singles and 1 Suite exist!
        bookingManager.enqueueRequest(new ReservationRequest("Alice", "Single"));
        bookingManager.enqueueRequest(new ReservationRequest("Bob", "Suite"));
        bookingManager.enqueueRequest(new ReservationRequest("Charlie", "Single"));
        bookingManager.enqueueRequest(new ReservationRequest("Diana", "Single")); // Should fail (sold out)

        // 5. Process Allocations (UC4)
        Map<String, String> confirmedBookings = bookingManager.processQueueString();

        // 6. Guests select Add-Ons (UC5)
        System.out.println("\n--- Adding Guest Services ---");
        AddOnService breakfast = new AddOnService("Breakfast", 25.0);
        AddOnService spa = new AddOnService("Spa", 120.0);

        if (confirmedBookings.containsKey("Alice")) {
            addons.addService(confirmedBookings.get("Alice"), breakfast);
            System.out.println("Added Breakfast for Alice.");
        }
        if (confirmedBookings.containsKey("Bob")) {
            addons.addService(confirmedBookings.get("Bob"), spa);
            addons.addService(confirmedBookings.get("Bob"), breakfast);
            System.out.println("Added Spa and Breakfast for Bob.");
        }

        // 7. Admin Generates Final Audit Report (UC6)
        reporting.generateAuditReport(addons);
        
        // 8. Final Inventory Check to prove consistency
        search.displayAvailableRooms();
    }
}