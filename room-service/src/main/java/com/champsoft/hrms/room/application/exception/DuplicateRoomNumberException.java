package com.champsoft.hrms.room.application.exception;

public class DuplicateRoomNumberException extends RuntimeException {
    public DuplicateRoomNumberException(String message) {
        super(message);
    }
}
