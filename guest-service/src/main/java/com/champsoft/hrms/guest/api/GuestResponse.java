package com.champsoft.hrms.guest.api;

public record GuestResponse(
        String id,
        String fullName,
        String address,
        String status) {}