package com.services;
import java.util.HashMap;
import java.util.Map;

// Core Data Source (from UC1, extended for amenities)
public class InventoryService {
	private Map<String, Integer> roomCounts;

	public InventoryService() {
		this.roomCounts = new HashMap<>();
	}

	public void addRoomType(String type, int initialCount) {
		roomCounts.put(type, initialCount);
	}

	public int getAvailableCount(String type) {
		return roomCounts.getOrDefault(type, 0);
	}

	// Key Requirement: Update availability immediately
	public void decrementInventory(String type) {
		int currentCount = getAvailableCount(type);
		if (currentCount > 0) {
			roomCounts.put(type, currentCount - 1);
		}
	}

	public void displayInventory() {
		System.out.println("\n--- Real-Time Inventory Status ---");
		for (Map.Entry<String, Integer> entry : roomCounts.entrySet()) {
			System.out.printf("Type: %-8s | Available: %-2d%n", entry.getKey(), entry.getValue());
		}
		System.out.println("----------------------------------");
	}
	
}