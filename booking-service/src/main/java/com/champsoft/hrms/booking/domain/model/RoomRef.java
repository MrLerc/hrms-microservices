package com.champsoft.hrms.booking.domain.model;

public record RoomRef(String value) {
    public RoomRef {
        if (value == null) throw new IllegalArgumentException("roomId is required");
        value = value.trim();
        if (value.isEmpty()) throw new IllegalArgumentException("roomId is required");
    }
}