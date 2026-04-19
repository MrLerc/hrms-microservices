package com.champsoft.hrms.room.infrastructure.persistence;

import com.champsoft.hrms.room.application.port.out.RoomRepositoryPort;
import com.champsoft.hrms.room.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaRoomRepositoryAdapter implements RoomRepositoryPort {

    private final SpringDataRoomRepository jpa;

    public JpaRoomRepositoryAdapter(SpringDataRoomRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Room save(Room room) {
        RoomJpaEntity entity = toEntity(room);
        jpa.save(entity);
        return room;
    }

    @Override
    public Optional<Room> findById(RoomId id) {
        return jpa.findById(id.value()).map(this::toDomain);
    }

    @Override
    public boolean existsByRoomNumber(String roomNumber) {
        return jpa.existsByRoomNumber(roomNumber);
    }

    @Override
    public List<Room> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(RoomId id) {
        jpa.deleteById(id.value());
    }

    private RoomJpaEntity toEntity(Room room) {
        RoomJpaEntity entity = new RoomJpaEntity();
        entity.id = room.id().value();
        entity.roomNumber = room.roomNumber().value();
        entity.specs = new RoomDetailsEmbeddable(
                room.details().roomType(),
                room.details().pricePerNight()

        );
        entity.status = room.status().name();
        return entity;
    }

    private Room toDomain(RoomJpaEntity entity) {
        Room room = new Room(
                RoomId.of(entity.id),
                new RoomNumber(entity.roomNumber),
                new RoomDetails(
                        entity.specs.pricePerNight,
                        entity.specs.roomType
                )
        );

        if ("OCCUPIED".equalsIgnoreCase(entity.status)) {
            room.occupy();
        }

        return room;
    }
}