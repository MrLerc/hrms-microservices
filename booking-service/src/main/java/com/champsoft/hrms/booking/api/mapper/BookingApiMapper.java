package com.champsoft.hrms.booking.api.mapper;

import com.champsoft.hrms.booking.api.BookingResponse;
import com.champsoft.hrms.booking.domain.model.Booking;

public class BookingApiMapper {

    public static BookingResponse toResponse(Booking b) {
        return new BookingResponse(
                b.id().value(),
                b.roomIdValue(),
                b.guestIdValue(),
                b.paymentIdValue(),
                b.startDateValue(),
                b.endDateValue(),
                b.status().name()
        );
    }
}