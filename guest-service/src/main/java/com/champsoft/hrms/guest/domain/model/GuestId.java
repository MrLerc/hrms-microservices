package com.champsoft.hrms.guest.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class GuestId {
    private final String value;

    private GuestId(String value) {
        this.value = value;
    }

    public static GuestId newId() {
        return new GuestId(UUID.randomUUID().toString());
    }

    public static GuestId of(String value) {
        return new GuestId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof GuestId other) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}