package com.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceManagementModule {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Success: Added '" + service.getName() + "' to Reservation " + reservationId);
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services != null && !services.isEmpty()) {
            System.out.print("   -> Add-Ons: ");
            double total = 0;
            for (AddOnService s : services) {
                System.out.print(s.getName() + " ($" + s.getPrice() + ") | ");
                total += s.getPrice();
            }
            System.out.println("\n   -> Add-On Total: $" + total);
        }
    }
}
