package com.main;
import com.services.*;
import com.reservation.*;

//Actor: Guest (represented via the main method)
public class Main {    
	public static void main(String[] args) {
		System.out.println("=== BookMyStay App: Use Case 4 ===");

		// 1. Initialize Inventory (Only 2 Single rooms, 1 Suite available)
		InventoryService inventory = new InventoryService();
		inventory.addRoomType("Single", 2);
		inventory.addRoomType("Suite", 1);

		inventory.displayInventory();

		// 2. Initialize Booking Service
		BookingService bookingService = new BookingService(inventory);

		// 3. Guests make requests (We have 3 requests for 'Single', but only 2 exist!)
		bookingService.enqueueRequest(new ReservationRequest("Alice", "Single"));
		bookingService.enqueueRequest(new ReservationRequest("Bob", "Suite"));
		bookingService.enqueueRequest(new ReservationRequest("Charlie", "Single"));
		bookingService.enqueueRequest(new ReservationRequest("Diana", "Single")); // This one should fail due to atomic allocation

		// 4. Process the Queue (Dequeue -> Assign -> Add to Set -> Decrement)
		bookingService.processQueue();

		// 5. Verify Strong booking integrity and Instant inventory sync
		bookingService.displayAllocations();
		inventory.displayInventory();
	}
}

