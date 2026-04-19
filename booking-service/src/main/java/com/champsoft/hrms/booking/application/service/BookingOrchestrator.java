package com.champsoft.hrms.booking.application.service;

import com.champsoft.hrms.booking.application.exception.*;
import com.champsoft.hrms.booking.application.port.out.*;
import com.champsoft.hrms.booking.domain.model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BookingOrchestrator {

    private final RoomEligibilityPort roomPort;
    private final GuestEligibilityPort guestPort;
    private final PaymentEligibilityPort paymentPort;
    private final BookingRepositoryPort repo;

    public BookingOrchestrator(
            RoomEligibilityPort roomPort,
            GuestEligibilityPort guestPort,
            PaymentEligibilityPort paymentPort,
            BookingRepositoryPort repo
    ) {
        this.roomPort = roomPort;
        this.guestPort = guestPort;
        this.paymentPort = paymentPort;
        this.repo = repo;
    }

    @Transactional
    public Booking create(
            String roomId,
            String guestId,
            String paymentId,
            LocalDate startDate,
            LocalDate endDate
    ) {

        if (!roomPort.isEligible(roomId)) {
            throw new CrossContextValidationException("Room not available");
        }

        if (!guestPort.isEligible(guestId)) {
            throw new CrossContextValidationException("Guest not eligible");
        }

        if (!paymentPort.isEligible(paymentId)) {
            throw new CrossContextValidationException("Payment not valid");
        }

        var booking = new Booking(
                BookingId.newId(),
                new RoomRef(roomId),
                new GuestRef(guestId),
                new PaymentRef(paymentId),
                startDate,
                endDate
        );

        return repo.save(booking);
    }
}