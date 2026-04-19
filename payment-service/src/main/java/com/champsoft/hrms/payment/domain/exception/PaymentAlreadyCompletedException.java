package com.champsoft.hrms.payment.domain.exception;

public class PaymentAlreadyCompletedException extends RuntimeException {
    public PaymentAlreadyCompletedException(String message) {
        super(message);
    }
}
