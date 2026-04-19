package com.champsoft.hrms.room.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataRoomRepository extends JpaRepository<RoomJpaEntity, String> {
    Optional<RoomJpaEntity> findByRoomNumber(String roomNumber);
    boolean existsByRoomNumber(String roomNumber);
}
