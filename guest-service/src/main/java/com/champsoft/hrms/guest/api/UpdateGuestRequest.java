package com.champsoft.hrms.guest.api;

import jakarta.validation.constraints.NotBlank;

public record UpdateGuestRequest(
        @NotBlank String fullName,
        String address) {}