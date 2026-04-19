package com.champsoft.hrms.guest.application.exception;

public class DuplicateGuestException extends RuntimeException {
    public DuplicateGuestException(String message) {
        super(message);
    }
}
