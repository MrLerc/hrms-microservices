package com.champsoft.hrms.booking.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataBookingRepository
        extends JpaRepository<BookingJpaEntity, String> {

    Optional<BookingJpaEntity> findByRoomId(String roomId);

    Optional<BookingJpaEntity> findByGuestId(String guestId);

    Optional<BookingJpaEntity> findByPaymentId(String paymentId);

    boolean existsByRoomId(String roomId);
}