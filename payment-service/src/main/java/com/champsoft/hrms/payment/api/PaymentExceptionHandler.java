package com.champsoft.hrms.payment.api;

import com.champsoft.hrms.payment.application.exception.PaymentNotFoundException;
import com.champsoft.hrms.payment.domain.exception.InvalidCardNumberException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = PaymentController.class)
public class PaymentExceptionHandler {

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<String> notFound(
            PaymentNotFoundException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(InvalidCardNumberException.class)
    public ResponseEntity<String> badRequest(
            InvalidCardNumberException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badRequestGeneric(
            IllegalArgumentException ex,
            HttpServletRequest req
    ) {
        return build(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .orElse("Validation failed");

        return build(HttpStatus.BAD_REQUEST, new IllegalArgumentException(message));
    }

    private ResponseEntity<String> build(
            HttpStatus status,
            Exception ex
    ) {
        return ResponseEntity.status(status).body(ex.getMessage());
    }
}