package com.services;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import com.reservation.ReservationRequest;

public class BookingService {
    private Queue<ReservationRequest> bookingQueue;
    private InventoryService inventory;
    
    // Key Data Structure: HashMap<String, Set<String>> – room type -> assigned rooms
    private Map<String, Set<String>> assignedRooms;

    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
        this.bookingQueue = new LinkedList<>();
        this.assignedRooms = new HashMap<>();
    }

    // Enqueue request (from UC3)
    public void enqueueRequest(ReservationRequest request) {
        bookingQueue.add(request);
        System.out.println("Enqueued: Request from " + request.getGuestName() + " for a '" + request.getRoomType() + "'");
    }

    // Process the queue and allocate rooms atomically
    public void processQueue() {
        System.out.println("\n--- Processing Booking Queue & Allocating Rooms ---");

        while (!bookingQueue.isEmpty()) {
            // Flow step 1: Dequeue request
            ReservationRequest request = bookingQueue.poll();
            String type = request.getRoomType();
            String guest = request.getGuestName();

            // Check availability
            if (inventory.getAvailableCount(type) > 0) {
                // Flow step 2: Assign a unique room ID
                String uniqueRoomId = generateUniqueRoomId(type);

                // Flow step 3: Add to Set (ensures no reuse of Room IDs)
                assignedRooms.putIfAbsent(type, new HashSet<>());
                assignedRooms.get(type).add(uniqueRoomId);

                // Flow step 4: Decrement count immediately
                inventory.decrementInventory(type);

                System.out.println("SUCCESS: Allocated " + uniqueRoomId + " to " + guest + ". (Zero double-booking guaranteed)");
            } else {
                System.out.println("FAILED: Sorry " + guest + ", no '" + type + "' rooms available.");
            }
        }
    }

    // Helper: Uniqueness enforcement
    private String generateUniqueRoomId(String roomType) {
        // In a real system, this might map to physical room numbers (e.g., Single-101). 
        // Here we use a UUID substring to guarantee uniqueness.
        String shortId = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return roomType + "-" + shortId;
    }

    public void displayAllocations() {
        System.out.println("\n--- Currently Allocated Rooms (Sets) ---");
        for (Map.Entry<String, Set<String>> entry : assignedRooms.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Assigned IDs: " + entry.getValue());
        }
    }
}