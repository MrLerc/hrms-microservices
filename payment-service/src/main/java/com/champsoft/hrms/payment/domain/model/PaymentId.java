package com.champsoft.hrms.payment.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class PaymentId {

    private final String value;

    private PaymentId(String value) {
        this.value = value;
    }

    public static PaymentId newId() {
        return new PaymentId(UUID.randomUUID().toString());
    }

    public static PaymentId of(String value) {
        return new PaymentId(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof PaymentId other)
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