package com.reservation;

//Represents a simple booking request made by a guest
public class ReservationRequest {
 private String guestName;
 private String roomType;

 public ReservationRequest(String guestName, String roomType) {
     this.guestName = guestName;
     this.roomType = roomType;
 }
 public String getGuestName() { return guestName; }
 public String getRoomType() { return roomType; }
}
