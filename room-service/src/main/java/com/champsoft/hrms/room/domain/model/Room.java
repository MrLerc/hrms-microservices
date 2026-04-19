package com.champsoft.hrms.room.domain.model;

import com.champsoft.hrms.room.domain.exception.RoomAlreadyOccupiedException;

public class Room {
    private final RoomId id;
    private final RoomNumber roomNumber;
    private RoomDetails details;
    private RoomStatus status;

    public Room(RoomId id, RoomNumber roomNumber, RoomDetails details) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.details = details;
        this.status = RoomStatus.AVAILABLE;
    }

    public RoomId id() { return id; }
    public RoomNumber roomNumber() { return roomNumber; }
    public RoomDetails details() { return details; }
    public RoomStatus status() { return status; }

    public void updateDetails(RoomDetails newDetails) {
        this.details = newDetails;
    }

    public void occupy() {
        if (status == RoomStatus.OCCUPIED) {
            throw new RoomAlreadyOccupiedException("Room is already OCCUPIED");
        }
        this.status = RoomStatus.OCCUPIED;
    }

    public boolean isEligibleForBooking() {
        return status == RoomStatus.AVAILABLE;
    }

}

