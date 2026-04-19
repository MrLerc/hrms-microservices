package com.champsoft.hrms.payment.domain.model;

import com.champsoft.hrms.payment.domain.exception.InvalidCardNumberException;

import java.util.Objects;

public final class CardNumber {

    private final String value;

    public CardNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidCardNumberException("Card number is required");
        }

        String v = value.trim();

        if (v.length() < 12 || v.length() > 19) {
            throw new InvalidCardNumberException("Card number must be between 12 and 19 digits");
        }

        if (!v.matches("\\d+")) {
            throw new InvalidCardNumberException("Card number must contain only digits");
        }

        this.value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof CardNumber other)
                && Objects.equals(value, other.value);
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
