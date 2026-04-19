package com.champsoft.hrms.booking.domain.model;

import com.champsoft.hrms.booking.domain.exception.InvalidBookingDateException;

import java.time.LocalDate;

public class Booking {

    private final BookingId id;
    private final RoomRef roomId;
    private final GuestRef guestId;
    private final PaymentRef paymentId;

    private final LocalDate startDate;
    private final LocalDate endDate;

    private BookingStatus status;

    public Booking(BookingId id,
                   RoomRef roomId,
                   GuestRef guestId,
                   PaymentRef paymentId,
                   LocalDate startDate,
                   LocalDate endDate) {

        if (startDate.isBefore(LocalDate.now())) {
            throw new InvalidBookingDateException("Start date cannot be in the past");
        }

        if (endDate.isBefore(startDate)) {
            throw new InvalidBookingDateException("End date cannot be before start date");
        }

        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.paymentId = paymentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = BookingStatus.CREATED;
    }

    // ===== Getters =====

    public BookingId id() { return id; }
    public RoomRef roomId() { return roomId; }
    public GuestRef guestId() { return guestId; }
    public PaymentRef paymentId() { return paymentId; }
    public LocalDate startDate() { return startDate; }
    public LocalDate endDate() { return endDate; }
    public BookingStatus status() { return status; }

    // ===== API convenience accessors =====

    public String idValue() { return id.value(); }
    public String roomIdValue() { return roomId.value(); }
    public String guestIdValue() { return guestId.value(); }
    public String paymentIdValue() { return paymentId.value(); }
    public LocalDate startDateValue() { return startDate; }
    public LocalDate endDateValue() { return endDate; }
    public String statusValue() { return status.name(); }

    // ===== Domain behavior =====

    public void confirm() {

        if (status != BookingStatus.CREATED) {
            throw new IllegalStateException("Booking must be CREATED to confirm");
        }

        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}