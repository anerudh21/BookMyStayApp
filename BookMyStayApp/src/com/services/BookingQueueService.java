package com.services;
import com.reservation.ReservationRequest;

import java.util.LinkedList;
import java.util.Queue;

// Actor: Booking Queue Service
public class BookingQueueService {
    // Key Data Structure: Queue to enforce FIFO 
    private Queue<ReservationRequest> bookingQueue;

    public BookingQueueService() {
        this.bookingQueue = new LinkedList<>();
    }

    // Key Requirement: Accept booking requests & Enforce arrival order 
    // Flow: Booking request -> Enqueue -> Await processing 
    public void enqueueRequest(ReservationRequest request) {
        bookingQueue.add(request);
        System.out.println("Enqueued: " + request.toString());
    }

    // Process the queue based on the Real-world fairness model 
    public void processQueue() {
        System.out.println("\n--- Processing Booking Queue (FIFO) ---");
        
        if (bookingQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
            return;
        }

        while (!bookingQueue.isEmpty()) {
            // Dequeue the oldest request
            ReservationRequest currentRequest = bookingQueue.poll();
            System.out.println("Processing: " + currentRequest.toString() + " | Arrival Time: " + currentRequest.getTimestamp());
            
            // In Use Case 4, we will connect this to actual inventory allocation.
            // For now, we simulate the processing phase.
            simulateProcessingDelay();
            System.out.println("SUCCESS: Processed request for " + currentRequest.getGuestName() + "\n");
        }
        
        System.out.println("--- All requests processed. Queue is empty. ---");
    }

    public int getQueueSize() {
        return bookingQueue.size();
    }

    // Helper to simulate system working through high-traffic scenarios
    private void simulateProcessingDelay() {
        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            System.out.println("Processing interrupted.");
        }
    }
}
