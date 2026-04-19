package com.champsoft.hrms.payment.api;

import com.champsoft.hrms.payment.domain.model.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePaymentRequest(
        @NotBlank String cardNumber,
        @NotBlank String expiryDate,
        @Positive double amount,
        @NotNull PaymentType type
) {}