package com.champsoft.hrms.booking.infrastructure.persistence;

import com.champsoft.hrms.booking.application.port.out.BookingRepositoryPort;
import com.champsoft.hrms.booking.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaBookingRepositoryAdapter implements BookingRepositoryPort {

    private final SpringDataBookingRepository jpa;

    public JpaBookingRepositoryAdapter(SpringDataBookingRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Booking save(Booking booking) {
        jpa.save(toEntity(booking));
        return booking;
    }

    @Override
    public Optional<Booking> findById(BookingId id) {
        return jpa.findById(id.value()).map(this::toDomain);
    }

    @Override
    public List<Booking> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(BookingId id) {
        jpa.deleteById(id.value());
    }

    // ======================
    // ENTITY → DOMAIN
    // ======================
    private Booking toDomain(BookingJpaEntity e) {

        var booking = new Booking(
                BookingId.of(e.id),
                new RoomRef(e.roomId),
                new GuestRef(e.guestId),
                new PaymentRef(e.paymentId),
                e.startDate,
                e.endDate
        );

        if ("CANCELLED".equalsIgnoreCase(e.status)) {
            booking.cancel();
        }

        return booking;
    }

    // ======================
    // DOMAIN → ENTITY
    // ======================
    private BookingJpaEntity toEntity(Booking b) {

        var e = new BookingJpaEntity();

        e.id = b.id().value();
        e.roomId = b.roomId().value();
        e.guestId = b.guestId().value();
        e.paymentId = b.paymentId().value();
        e.startDate = b.startDate();
        e.endDate = b.endDate();
        e.status = b.status().name();

        return e;
    }
}