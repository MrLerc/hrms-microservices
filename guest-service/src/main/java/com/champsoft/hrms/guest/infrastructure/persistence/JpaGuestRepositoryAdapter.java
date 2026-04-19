package com.champsoft.hrms.guest.infrastructure.persistence;

import com.champsoft.hrms.guest.application.port.out.GuestRepositoryPort;
import com.champsoft.hrms.guest.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaGuestRepositoryAdapter implements GuestRepositoryPort {

    private final SpringDataGuestRepository jpa;

    public JpaGuestRepositoryAdapter(SpringDataGuestRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Guest save(Guest guest) {
        jpa.save(toEntity(guest));
        return guest;
    }

    @Override
    public Optional<Guest> findById(GuestId id) {
        return jpa.findById(id.value()).map(this::toDomain);
    }

    @Override
    public boolean existsByFullName(String fullName) {
        return jpa.existsByFullNameIgnoreCase(fullName);
    }

    @Override
    public List<Guest> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(GuestId id) {
        jpa.deleteById(id.value());
    }

    private GuestJpaEntity toEntity(Guest g) {
        var e = new GuestJpaEntity();
        e.id = g.id().value();
        e.fullName = g.fullName().value();
        e.address = g.address().value();
        e.status = g.status().name();
        return e;
    }

    private Guest toDomain(GuestJpaEntity e) {
        var guest = new Guest(
                GuestId.of(e.id),
                new FullName(e.fullName),
                new Address(e.address)
        );
        if ("ACTIVE".equalsIgnoreCase(e.status)) guest.activate();
        if ("SUSPENDED".equalsIgnoreCase(e.status)) guest.suspend();
        return guest;
    }
}