package com.champsoft.hrms.room.api.dto;

import com.champsoft.hrms.room.domain.model.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateRoomRequest(
        @NotBlank String roomNumber,
        @NotNull RoomType roomType,
        @Positive double pricePerNight
) {}