package com.champsoft.hrms.payment.domain.exception;

public class InvalidExpiryDateException extends RuntimeException {
    public InvalidExpiryDateException(String message) {
        super(message);
    }
}
