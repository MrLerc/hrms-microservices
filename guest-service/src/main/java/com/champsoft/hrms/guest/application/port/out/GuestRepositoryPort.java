package com.champsoft.hrms.guest.application.port.out;

import com.champsoft.hrms.guest.domain.model.Guest;
import com.champsoft.hrms.guest.domain.model.GuestId;

import java.util.List;
import java.util.Optional;

public interface GuestRepositoryPort {
    Guest save(Guest guest);
    Optional<Guest> findById(GuestId id);
    boolean existsByFullName(String fullName);
    List<Guest> findAll();
    void deleteById(GuestId id);
}