package com.champsoft.hrms.guest.domain.model;

import com.champsoft.hrms.guest.domain.exception.InvalidAddressException;

public final class Address {
    private final String value;

    public Address(String value) {
        if (value == null) {
            this.value = null; // allow null address
            return;
        }
        String v = value.trim();
        if (v.length() > 200) throw new InvalidAddressException("Address max length is 200");
        this.value = v.isEmpty() ? null : v;
    }

    public String value() { return value; }
}