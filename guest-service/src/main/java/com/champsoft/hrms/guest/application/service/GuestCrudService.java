package com.champsoft.hrms.guest.application.service;

import com.champsoft.hrms.guest.application.exception.DuplicateGuestException;
import com.champsoft.hrms.guest.application.exception.GuestNotFoundException;
import com.champsoft.hrms.guest.application.port.out.GuestRepositoryPort;
import com.champsoft.hrms.guest.domain.model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestCrudService {

    private final GuestRepositoryPort repo;

    public GuestCrudService(GuestRepositoryPort repo) {
        this.repo = repo;
    }

    @Transactional
    public Guest create(String fullName, String address) {
        var name = new FullName(fullName);
        String key = name.value();
        if (repo.existsByFullName(key)) {
            throw new DuplicateGuestException("Guest already exists by name: " + key);
        }
        var guest = new Guest(GuestId.newId(), name, new Address(address));
        return repo.save(guest);
    }

    @Transactional(readOnly = true)
    public Guest getById(String id) {
        return repo.findById(GuestId.of(id))
                .orElseThrow(() -> new GuestNotFoundException("Guest not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Guest> list() {
        return repo.findAll();
    }

    @Transactional
    public Guest update(String id, String fullName, String address) {
        var guest = getById(id);
        guest.update(new FullName(fullName), new Address(address));
        return repo.save(guest);
    }

    @Transactional
    public Guest activate(String id) {
        var guest = getById(id);
        guest.activate();
        return repo.save(guest);
    }

    @Transactional
    public Guest suspend(String id) {
        var guest = getById(id);
        guest.suspend();
        return repo.save(guest);
    }

    @Transactional
    public void delete(String id) {
        getById(id);
        repo.deleteById(GuestId.of(id));
    }
}