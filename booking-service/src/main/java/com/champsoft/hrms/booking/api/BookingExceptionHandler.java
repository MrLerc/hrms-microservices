package com.champsoft.hrms.booking.api;

import com.champsoft.hrms.booking.application.exception.*;
import com.champsoft.hrms.booking.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookingController.class)
public class BookingExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> notFound(
            BookingNotFoundException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<String> conflict(
            RoomNotAvailableException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(CrossContextValidationException.class)
    public ResponseEntity<String> unprocessable(
            CrossContextValidationException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.valueOf(422), ex);
    }

    @ExceptionHandler({
            InvalidBookingDateException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<String> badRequest(
            RuntimeException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<String> build(
            HttpStatus status,
            Exception ex
    ) {
        return ResponseEntity.status(status).body(ex.getMessage());
    }
}