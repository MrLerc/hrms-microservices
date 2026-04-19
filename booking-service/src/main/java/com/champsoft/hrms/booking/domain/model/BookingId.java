package com.champsoft.hrms.booking.domain.model;

import java.util.UUID;

public final class BookingId {

    private final String value;

    private BookingId(String value) {
        this.value = value;
    }

    public static BookingId newId() {
        return new BookingId(UUID.randomUUID().toString());
    }

    public static BookingId of(String value) {
        return new BookingId(value);
    }

    public String value() {
        return value;
    }
}