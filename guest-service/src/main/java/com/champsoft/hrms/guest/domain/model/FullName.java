package com.champsoft.hrms.guest.domain.model;

import com.champsoft.hrms.guest.domain.exception.InvalidGuestNameException;
import java.util.Objects;

public final class FullName {
    private final String value;

    public FullName(String value) {
        if (value == null) throw new InvalidGuestNameException("Full name is required");
        String v = value.trim();
        if (v.length() < 2 || v.length() > 120) throw new InvalidGuestNameException("Full name length must be 2..120");
        this.value = v;
    }

    public String value() { return value; }

    @Override
    public boolean equals(Object o) {
        return (o instanceof FullName other) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public String toString() { return value; }
}