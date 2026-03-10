package com.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceManagementModule {
 // Key Data Structure: Map<String, List<Service>> - reservation ID -> services
 private Map<String, List<ActualService>> reservationServices;

 public ServiceManagementModule() {
     this.reservationServices = new HashMap<>();
 }

 // Flow: Select service -> Add to List -> Map to reservation ID
 public void addServiceToReservation(String reservationId, ActualService service) {
     // Use computeIfAbsent to initialize the List if this is the first service added
     reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
     System.out.println("Success: Attached " + service.getName() + " to Reservation ID: " + reservationId);
 }

 // Key Requirement: Calculate the additional cost
 public double calculateTotalServiceCost(String reservationId) {
     if (!reservationServices.containsKey(reservationId)) {
         return 0.0;
     }

     double totalCost = 0;
     for (ActualService service : reservationServices.get(reservationId)) {
         totalCost += service.getPrice();
     }
     return totalCost;
 }

 // Helper method to display a guest's itinerary
 public void displayReservationServices(String reservationId) {
     System.out.println("\n--- Add-On Services for Reservation: " + reservationId + " ---");
     List<ActualService> services = reservationServices.get(reservationId);
     
     if (services == null || services.isEmpty()) {
         System.out.println("No add-on services selected.");
     } else {
         for (ActualService service : services) {
             System.out.println(" - " + service.toString());
         }
         System.out.printf("Total Additional Cost: $%.2f%n", calculateTotalServiceCost(reservationId));
     }
     System.out.println("---------------------------------------------------");
 }
}
