package com.champsoft.hrms.booking.api.dto;

import java.time.LocalDate;

public record BookingResponse(

        String id,
        String roomId,
        String guestId,
        String paymentId,
        LocalDate startDate,
        LocalDate endDate,
        String status

) {}