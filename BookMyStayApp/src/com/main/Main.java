package com.main;
import com.services.*;
//Actor: Guest (represented via the main method)
public class Main {    

	public static void main(String[] args) {
		System.out.println("=== BookMyStay App: Use Case 5 ===");

		// 1. Initialize the Service Management Module
		ServiceManagementModule serviceModule = new ServiceManagementModule();

		// 2. Define our Extensible service model (Breakfast, Spa, Pickup)
		ActualService breakfast = new ActualService("Continental Breakfast", 25.00);
		ActualService spa = new ActualService("Full Body Spa Massage", 120.00);
		ActualService airportPickup = new ActualService("Airport VIP Pickup", 50.00);

		// Simulated Reservation IDs from Use Case 4
		String aliceReservationId = "Single-A1B2";
		String bobReservationId = "Suite-X9Y8";

		// 3. Guest Flow: Select service -> Add to List -> Map to reservation ID
		System.out.println("\n[Guest Action: Alice adding services]");
		serviceModule.addServiceToReservation(aliceReservationId, breakfast);
		serviceModule.addServiceToReservation(aliceReservationId, airportPickup);

		System.out.println("\n[Guest Action: Bob adding services]");
		serviceModule.addServiceToReservation(bobReservationId, spa);
		serviceModule.addServiceToReservation(bobReservationId, breakfast);

		// 4. View results and calculate additional costs
		serviceModule.displayReservationServices(aliceReservationId);
		serviceModule.displayReservationServices(bobReservationId);

		// Demonstrating an empty mapping for a guest who opted out of add-ons
		String charlieReservationId = "Single-Z7C4";
		serviceModule.displayReservationServices(charlieReservationId);
	}
}


