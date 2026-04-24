package com.champsoft.hrms.room.application.service;

import com.champsoft.hrms.room.application.exception.DuplicateRoomNumberException;
import com.champsoft.hrms.room.application.exception.RoomNotFoundException;
import com.champsoft.hrms.room.application.port.out.RoomRepositoryPort;
import com.champsoft.hrms.room.domain.model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomCrudService {

    private final RoomRepositoryPort repo;

    public RoomCrudService(RoomRepositoryPort repo) {
        this.repo = repo;
    }

    @Transactional
    public Room create(String roomNumber, double pricePerNight, RoomType roomType) {

        if (repo.existsByRoomNumber(roomNumber)) {
            throw new DuplicateRoomNumberException("Room number already exists: " + roomNumber);
        }

        var room = new Room(
                RoomId.newId(),
                new RoomNumber(roomNumber),
                new RoomDetails(pricePerNight, roomType)
        );
        return repo.save(room);
    }

    @Transactional(readOnly = true)
    public Room getById(String id) {
        return repo.findById(RoomId.of(id))
                .orElseThrow(() -> new RoomNotFoundException("Room not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Room> list() {
        return repo.findAll();
    }

    @Transactional
    public Room updateDetails(String id, double pricePerNight, RoomType roomType) {
        var room = getById(id);
        room.updateDetails(new RoomDetails(pricePerNight, roomType));
        return repo.save(room);
    }

    @Transactional
    public Room occupy(String id) {
        var room = getById(id);
        room.occupy();
        return repo.save(room);
    }

    @Transactional
    public void delete(String id) {
        getById(id); // ensures room exists
        repo.deleteById(RoomId.of(id));
    }
}