package com.champsoft.hrms.booking.application.port.out;

public interface RoomEligibilityPort {
    boolean isEligible(String roomId);
}
