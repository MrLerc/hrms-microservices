package com.champsoft.hrms.guest.api;

import com.champsoft.hrms.guest.api.mapper.GuestApiMapper;
import com.champsoft.hrms.guest.application.service.GuestCrudService;
import com.champsoft.hrms.guest.application.service.GuestEligibilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestCrudService service;
    private final GuestEligibilityService guestEligibilityService;

    public GuestController(GuestCrudService service,
                           GuestEligibilityService guestEligibilityService) {
        this.service = service;
        this.guestEligibilityService = guestEligibilityService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateGuestRequest req) {
        var g = service.create(req.fullName(), req.address());
        return ResponseEntity.ok(GuestApiMapper.toResponse(g));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return ResponseEntity.ok(GuestApiMapper.toResponse(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(
                service.list().stream().map(GuestApiMapper::toResponse).toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid UpdateGuestRequest req) {
        var g = service.update(id, req.fullName(), req.address());
        return ResponseEntity.ok(GuestApiMapper.toResponse(g));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<?> activate(@PathVariable String id) {
        var g = service.activate(id);
        return ResponseEntity.ok(GuestApiMapper.toResponse(g));
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<?> suspend(@PathVariable String id) {
        var g = service.suspend(id);
        return ResponseEntity.ok(GuestApiMapper.toResponse(g));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/eligibility")
    public boolean isEligible(@PathVariable String id) {
        return guestEligibilityService.isEligible(id);
    }
}