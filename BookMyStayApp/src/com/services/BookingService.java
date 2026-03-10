package com.services;

import java.util.*;
import com.reservation.*;

public class BookingService {
    private Queue<ReservationRequest> bookingQueue = new LinkedList<>();
    private Map<String, Set<String>> assignedRooms = new HashMap<>();
    
    private InventoryService inventory;
    private ReportingService reportingService;

    public BookingService(InventoryService inventory, ReportingService reportingService) {
        this.inventory = inventory;
        this.reportingService = reportingService;
    }

    public void enqueueRequest(ReservationRequest request) {
        bookingQueue.add(request);
        System.out.println("Enqueued Request: " + request.getGuestName() + " wants a '" + request.getRoomType() + "'");
    }
    
    public Map<String, String> processQueueString() {
        System.out.println("\n--- Processing Booking Queue ---");
        Map<String, String> successfulBookings = new HashMap<>(); // Guest Name -> Reservation ID

        while (!bookingQueue.isEmpty()) {
            ReservationRequest request = bookingQueue.poll();
            String type = request.getRoomType();
            String guest = request.getGuestName();

            if (inventory.getAvailableCount(type) > 0) {
                // Generate unique ID & allocate
                String uniqueRoomId = type + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
                assignedRooms.computeIfAbsent(type, k -> new HashSet<>()).add(uniqueRoomId);
                
                // Deduct inventory
                inventory.decrementInventory(type);

                // Create confirmed record and send to Reporting (UC6)
                Reservation newReservation = new Reservation(uniqueRoomId, guest, type);
                reportingService.addConfirmedReservation(newReservation);
                
                successfulBookings.put(guest, uniqueRoomId);
                System.out.println("SUCCESS: " + guest + " booked " + uniqueRoomId);
            } else {
                System.out.println("FAILED: Sorry " + guest + ", '" + type + "' is sold out.");
            }
        }
        return successfulBookings;
    }
    
    public void processQueue() {
        System.out.println("\n--- Processing Booking Queue ---");
        if (bookingQueue.isEmpty()) {
            System.out.println("Queue is empty. No requests to process.");
            return;
        }

        while (!bookingQueue.isEmpty()) {
            ReservationRequest request = bookingQueue.poll();
            String type = request.getRoomType();
            String guest = request.getGuestName();

            if (inventory.getAvailableCount(type) > 0) {
                String uniqueRoomId = type + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
                assignedRooms.computeIfAbsent(type, k -> new HashSet<>()).add(uniqueRoomId);
                
                inventory.decrementInventory(type);

                Reservation newReservation = new Reservation(uniqueRoomId, guest, type);
                reportingService.addConfirmedReservation(newReservation);
                
                System.out.println("SUCCESS: " + guest + " booked room. [Reservation ID: " + uniqueRoomId + "]");
            } else {
                System.out.println("FAILED: Sorry " + guest + ", '" + type + "' is sold out. (Zero Double-Booking Enforced)");
            }
        }
    }
}