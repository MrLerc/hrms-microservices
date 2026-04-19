package com.champsoft.hrms.booking.domain.model;

public record GuestRef(String value) {
    public GuestRef {
        if (value == null) throw new IllegalArgumentException("guestId is required");
        value = value.trim();
        if (value.isEmpty()) throw new IllegalArgumentException("guestId is required");
    }
}
