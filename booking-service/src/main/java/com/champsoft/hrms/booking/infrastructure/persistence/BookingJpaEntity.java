package com.champsoft.hrms.booking.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class BookingJpaEntity {

    @Id
    public String id;

    @Column(name = "room_id", nullable = false)
    public String roomId;

    @Column(name = "guest_id", nullable = false)
    public String guestId;

    @Column(name = "payment_id", nullable = false)
    public String paymentId;

    @Column(name = "start_date", nullable = false)
    public LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    public LocalDate endDate;

    @Column(nullable = false)
    public String status;
}
