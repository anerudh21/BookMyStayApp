package com.reservation;

public class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String status;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.status = "CONFIRMED";
    }
    public String getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("ID: %-12s | Guest: %-8s | Room: %-8s | Status: %s", 
                             reservationId, guestName, roomType, status);
    }
}