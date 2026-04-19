package com.champsoft.hrms.room.domain.exception;

public class RoomAlreadyOccupiedException extends RuntimeException {
    public RoomAlreadyOccupiedException(String message) {
        super(message);
    }
}
