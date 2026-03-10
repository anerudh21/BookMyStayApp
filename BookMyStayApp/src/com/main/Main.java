package com.main;
import com.reservation.ReservationRequest;
import com.services.*;
import java.util.*;

//Actor: Guest (represented via the main method)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        InventoryService inventory = new InventoryService();
        SearchService search = new SearchService(inventory);
        ReportingService reporting = new ReportingService();
        ServiceManagementModule addons = new ServiceManagementModule();
        BookingService bookingManager = new BookingService(inventory, reporting);

        System.out.println("Welcome to the BookMyStay Terminal App!");

        boolean running = true;
        while (running) {
            System.out.println("\n=================================");
            System.out.println("1. [Admin] Add Room to Inventory (UC1)");
            System.out.println("2. [Guest] Search Available Rooms (UC2)");
            System.out.println("3. [Guest] Submit Booking Request (UC3)");
            System.out.println("4. [System] Process Booking Queue (UC4)");
            System.out.println("5. [Guest] Add Service to Reservation (UC5)");
            System.out.println("6. [Admin] View Booking Audit Report (UC6)");
            System.out.println("7. Exit");
            System.out.println("=================================");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            // Advanced Switch Case (Java 14+)
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Room Type (e.g., Single, Suite): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Initial Count: ");
                    int count = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Price per night: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    inventory.addRoomType(type, count, price);
                }
                case 2 -> search.displayAvailableRooms();
                case 3 -> {
                    System.out.print("Enter Guest Name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter Requested Room Type: ");
                    String roomType = scanner.nextLine();
                    bookingManager.enqueueRequest(new ReservationRequest(guestName, roomType));
                }
                case 4 -> bookingManager.processQueue();
                case 5 -> {
                    System.out.print("Enter your confirmed Reservation ID (e.g., Single-ABCD): ");
                    String resId = scanner.nextLine();
                    System.out.print("Enter Service Name (e.g., Spa, Breakfast): ");
                    String serviceName = scanner.nextLine();
                    System.out.print("Enter Service Price: ");
                    double servicePrice = Double.parseDouble(scanner.nextLine());
                    addons.addService(resId, new AddOnService(serviceName, servicePrice));
                }
                case 6 -> reporting.generateAuditReport(addons);
                case 7 -> {
                    System.out.println("Exiting BookMyStay App. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }}
