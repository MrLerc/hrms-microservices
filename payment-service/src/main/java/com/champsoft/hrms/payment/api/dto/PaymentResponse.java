package com.champsoft.hrms.payment.api.dto;

import com.champsoft.hrms.payment.domain.model.PaymentType;

public record PaymentResponse(
        String id,
        String cardNumber,
        String expiryDate,
        double amount,
        PaymentType type,
        String status
) {}