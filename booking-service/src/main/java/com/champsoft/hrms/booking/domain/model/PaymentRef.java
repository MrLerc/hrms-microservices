package com.champsoft.hrms.booking.domain.model;

public record PaymentRef(String value) {
    public PaymentRef {
        if (value == null) throw new IllegalArgumentException("paymentId is required");
        value = value.trim();
        if (value.isEmpty()) throw new IllegalArgumentException("paymentId is required");
    }
}