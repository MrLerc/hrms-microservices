package com.champsoft.hrms.booking.application.port.out;

public interface PaymentEligibilityPort {
    boolean isEligible(String paymentId);
}
