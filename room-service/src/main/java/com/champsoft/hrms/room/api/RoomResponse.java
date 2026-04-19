package com.champsoft.hrms.room.api;

import com.champsoft.hrms.room.domain.model.RoomType;

public record RoomResponse(
        String id,
        String roomNumber,
        RoomType roomType,
        double pricePerNight,
        String status
) {}