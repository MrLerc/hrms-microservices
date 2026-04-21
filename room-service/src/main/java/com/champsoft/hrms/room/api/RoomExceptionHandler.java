package com.champsoft.hrms.room.api;

import com.champsoft.hrms.room.application.exception.DuplicateRoomNumberException;
import com.champsoft.hrms.room.application.exception.RoomNotFoundException;
import com.champsoft.hrms.room.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RoomController.class)
public class RoomExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> notFound(RoomNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(DuplicateRoomNumberException.class)
    public ResponseEntity<?> handleDuplicateRoomNumber(DuplicateRoomNumberException ex,
                                                       HttpServletRequest req) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RoomAlreadyOccupiedException.class)
    public ResponseEntity<String> conflict(RoomAlreadyOccupiedException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler({ InvalidRoomNumberException.class, IllegalArgumentException.class })
    public ResponseEntity<String> badRequest(RuntimeException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> badRequest(MethodArgumentNotValidException ex, HttpServletRequest req) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .orElse("Validation failed");
        return build(HttpStatus.BAD_REQUEST, new IllegalArgumentException(message));
    }

    private ResponseEntity<String> build(HttpStatus status, Exception ex) {
        return ResponseEntity
                .status(status)
                .body(ex.getMessage());
    }
}