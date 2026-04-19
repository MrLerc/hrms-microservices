package com.champsoft.hrms.booking.api;

import com.champsoft.hrms.booking.api.*;
import com.champsoft.hrms.booking.api.mapper.BookingApiMapper;
import com.champsoft.hrms.booking.application.service.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingOrchestrator orchestrator;
    private final BookingCrudService crud;
    private final BookingRepresentationAssembler assembler;

    public BookingController(
            BookingOrchestrator orchestrator,
            BookingCrudService crud,
            BookingRepresentationAssembler assembler
    ) {
        this.orchestrator = orchestrator;
        this.crud = crud;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<BookingResponse>> create(
            @RequestBody @Valid BookRoomRequest req
    ) {
        var booking = orchestrator.create(
                req.roomId(),
                req.guestId(),
                req.paymentId(),
                req.startDate(),
                req.endDate()
        );

        var response = BookingApiMapper.toResponse(booking);
        return ResponseEntity.ok(assembler.toModel(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookingResponse>> get(@PathVariable String id) {
        var response = BookingApiMapper.toResponse(crud.get(id));
        return ResponseEntity.ok(assembler.toModel(response));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<BookingResponse>>> list() {

        List<BookingResponse> responses = crud.list().stream()
                .map(BookingApiMapper::toResponse)
                .toList();

        return ResponseEntity.ok(assembler.toCollectionModel(responses));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<EntityModel<BookingResponse>> cancel(@PathVariable String id) {

        var booking = crud.cancel(id);
        var response = BookingApiMapper.toResponse(booking);

        return ResponseEntity.ok(assembler.toModel(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        crud.delete(id);
        return ResponseEntity.noContent().build();
    }
}