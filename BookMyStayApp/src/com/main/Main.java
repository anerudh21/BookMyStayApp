package com.main;

import com.reservation.ReservationRequest;
import com.services.BookingQueueService;

//Actor: Guest (represented via the main method)
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BookMyStay App: Use Case 3 ===");
        
        BookingQueueService queueService = new BookingQueueService();

        System.out.println("\n[Simulating High Traffic: Multiple Guests Requesting Rooms Simultaneously]");
        
        // Simulating guests submitting requests [cite: 108]
        queueService.enqueueRequest(new ReservationRequest("Alice", "Suite"));
        queueService.enqueueRequest(new ReservationRequest("Bob", "Single"));
        queueService.enqueueRequest(new ReservationRequest("Charlie", "Double"));
        queueService.enqueueRequest(new ReservationRequest("Diana", "Suite"));
        
        System.out.println("\nCurrent Queue Size: " + queueService.getQueueSize() + " requests pending.");

      // Processing the queue to demonstrate predictable booking order [cite: 110]
        queueService.processQueue();
    }
}
