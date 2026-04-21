package com.champsoft.hrms.payment.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePaymentRequest(
        @NotBlank String cardNumber,
        @NotBlank String expiryDate
) {}