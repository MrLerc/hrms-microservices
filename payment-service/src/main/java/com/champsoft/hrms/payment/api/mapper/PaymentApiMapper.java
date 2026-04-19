package com.champsoft.hrms.payment.api.mapper;

import com.champsoft.hrms.payment.api.PaymentResponse;
import com.champsoft.hrms.payment.domain.model.Payment;

public class PaymentApiMapper {

    public static PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.id().value(),
                payment.cardNumber().value(),
                payment.expiryDate(),
                payment.amount(),
                payment.type(),
                payment.status().name()
        );
    }
}