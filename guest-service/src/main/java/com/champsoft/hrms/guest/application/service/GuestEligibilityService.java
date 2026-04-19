package com.champsoft.hrms.guest.application.service;

import com.champsoft.hrms.guest.application.exception.GuestNotFoundException;
import com.champsoft.hrms.guest.application.port.out.GuestRepositoryPort;
import com.champsoft.hrms.guest.domain.model.GuestId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestEligibilityService {

    private final GuestRepositoryPort repo;

    public GuestEligibilityService(GuestRepositoryPort repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public boolean isEligible(String guestId) {
        return repo.findById(GuestId.of(guestId))
                .map(g -> g.isEligibleForRegistration())
                .orElseThrow(() -> new GuestNotFoundException("Guest not found: " + guestId));
    }
}