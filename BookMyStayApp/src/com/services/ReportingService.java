package com.services;

import java.util.ArrayList;
import java.util.List;

import com.reservation.Reservation;

public class ReportingService {
    private List<Reservation> bookingHistory = new ArrayList<>();

    public void addConfirmedReservation(Reservation reservation) {
        bookingHistory.add(reservation);
    }

    public void generateAuditReport(ServiceManagementModule serviceModule) {
        System.out.println("\n=== FULL SYSTEM AUDIT REPORT ===");
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings in the system yet.");
        } else {
            for (Reservation res : bookingHistory) {
                System.out.println(res.toString());
                serviceModule.displayServices(res.getReservationId());
            }
        }
        System.out.println("================================");
    }
}