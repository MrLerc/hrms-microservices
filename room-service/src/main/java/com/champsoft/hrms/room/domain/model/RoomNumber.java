
package com.champsoft.hrms.room.domain.model;

import com.champsoft.hrms.room.domain.exception.InvalidRoomNumberException;

import java.util.Objects;

public final class RoomNumber {

    private final String value;

    public RoomNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidRoomNumberException("RoomNumber is required");
        }

        String r = value.trim().toUpperCase();

        if (r.length() < 1 || r.length() > 5) {
            throw new InvalidRoomNumberException("RoomNumber must be between 1 and 5 characters");
        }

        this.value = r;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof RoomNumber other)
                && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return value; }
}