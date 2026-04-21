package com.champsoft.hrms.room.api.mapper;

import com.champsoft.hrms.room.api.dto.RoomResponse;
import com.champsoft.hrms.room.domain.model.Room;

public class RoomApiMapper {

    public static RoomResponse toResponse(Room room) {
        return new RoomResponse(
                room.id().value(),
                room.roomNumber().value(),
                room.details().roomType(),
                room.details().pricePerNight(),
                room.status().name()
        );
    }
}