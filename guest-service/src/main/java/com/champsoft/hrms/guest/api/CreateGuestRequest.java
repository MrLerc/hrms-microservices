package com.champsoft.hrms.guest.api;

import jakarta.validation.constraints.NotBlank;

public record CreateGuestRequest(
        @NotBlank String fullName,
        String address) {}