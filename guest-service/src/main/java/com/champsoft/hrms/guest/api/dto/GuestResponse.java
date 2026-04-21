package com.champsoft.hrms.guest.api.dto;

public record GuestResponse(
        String id,
        String fullName,
        String address,
        String status) {}