package com.champsoft.hrms.room.application.port.out;

import com.champsoft.hrms.room.domain.model.Room;
import com.champsoft.hrms.room.domain.model.RoomId;

import java.util.List;
import java.util.Optional;

public interface RoomRepositoryPort {
    Room save(Room room);
    Optional<Room> findById(RoomId id);
    boolean existsByRoomNumber(String roomNumber);
    List<Room> findAll();
    void deleteById(RoomId id);
}