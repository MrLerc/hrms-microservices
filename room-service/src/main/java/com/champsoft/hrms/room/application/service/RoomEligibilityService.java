package com.champsoft.hrms.room.application.service;

import com.champsoft.hrms.room.application.exception.RoomNotFoundException;
import com.champsoft.hrms.room.application.port.out.RoomRepositoryPort;
import com.champsoft.hrms.room.domain.model.RoomId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomEligibilityService {

    private final RoomRepositoryPort repo;

    public RoomEligibilityService(RoomRepositoryPort repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public boolean isEligible(String roomId) {
        return repo.findById(RoomId.of(roomId))
                .map(room -> room.isEligibleForBooking())
                .orElseThrow(() -> new RoomNotFoundException("Room not found: " + roomId));
    }
}