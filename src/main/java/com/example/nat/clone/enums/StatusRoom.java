package com.example.nat.clone.enums;

public enum StatusRoom {

    AVAILABLE("Available"),
    BOOKED("Booked"),
    MAINTENANCE("Maintenance"),
    OCCUPIED("Occupied");

    private final String status;

    StatusRoom(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
