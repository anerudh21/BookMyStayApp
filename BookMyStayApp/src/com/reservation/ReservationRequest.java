package com.reservation;


//Represents a simple booking request made by a guest
public class ReservationRequest {
 private String guestName;
 private String roomType;
 private long timestamp;

 public ReservationRequest(String guestName, String roomType) {
     this.guestName = guestName;
     this.roomType = roomType;
     this.timestamp = System.currentTimeMillis(); // Helps prove arrival order
 }

 public String getGuestName() { return guestName; }
 public String getRoomType() { return roomType; }
 public long getTimestamp() { return timestamp; }

 @Override
 public String toString() {
     return "Request from " + guestName + " for a '" + roomType + "' room.";
 }
}
