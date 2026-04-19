package com.champsoft.hrms.room.api;

import com.champsoft.hrms.room.api.mapper.RoomApiMapper;
import com.champsoft.hrms.room.application.service.RoomCrudService;
import com.champsoft.hrms.room.application.service.RoomEligibilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomCrudService service;
    private final RoomEligibilityService roomEligibilityService;

    public RoomController(RoomCrudService service,
                          RoomEligibilityService roomEligibilityService) {
        this.service = service;
        this.roomEligibilityService = roomEligibilityService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateRoomRequest req) {
        var room = service.create(req.roomNumber(), req.pricePerNight(), req.roomType());
        return ResponseEntity.ok(RoomApiMapper.toResponse(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        var room = service.getById(id);
        return ResponseEntity.ok(RoomApiMapper.toResponse(room));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        var data = service.list().stream().map(RoomApiMapper::toResponse).toList();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid UpdateRoomRequest req) {
        var room = service.updateDetails(id, req.pricePerNight(), req.roomType());
        return ResponseEntity.ok(RoomApiMapper.toResponse(room));
    }

    @PostMapping("/{id}/occupy")
    public ResponseEntity<?> occupy(@PathVariable String id) {
        var room = service.occupy(id);
        return ResponseEntity.ok(RoomApiMapper.toResponse(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/eligibility")
    public boolean isEligible(@PathVariable String id) {
        return roomEligibilityService.isEligible(id);
    }
}