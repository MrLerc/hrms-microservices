package com.champsoft.hrms.booking.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookRoomRequest(

        @NotBlank String roomId,
        @NotBlank String guestId,
        @NotBlank String paymentId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate

) {}