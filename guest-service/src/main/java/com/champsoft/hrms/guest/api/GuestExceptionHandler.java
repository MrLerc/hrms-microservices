package com.champsoft.hrms.guest.api;

import com.champsoft.hrms.guest.application.exception.*;
import com.champsoft.hrms.guest.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = GuestController.class)
public class GuestExceptionHandler {

    @ExceptionHandler(GuestNotFoundException.class)
    public ResponseEntity<String> notFound(GuestNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(DuplicateGuestException.class)
    public ResponseEntity<String> conflict(DuplicateGuestException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler({
            InvalidGuestNameException.class,
            InvalidAddressException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<String> badRequest(RuntimeException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex);
    }

    private ResponseEntity<String> build(HttpStatus status, Exception ex) {
        return ResponseEntity
                .status(status)
                .body(ex.getMessage());
    }
}