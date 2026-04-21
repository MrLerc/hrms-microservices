package com.champsoft.hrms.guest.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateGuestRequest(
        @NotBlank String fullName,
        String address) {}