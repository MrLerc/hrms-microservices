package com.champsoft.hrms.booking.application.port.out;

public interface GuestEligibilityPort {
    boolean isEligible(String guestId);
}
