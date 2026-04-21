package com.champsoft.hrms.room.api.dto;

import com.champsoft.hrms.room.domain.model.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateRoomRequest(
        @NotNull RoomType roomType,
        @Positive double pricePerNight
) {}