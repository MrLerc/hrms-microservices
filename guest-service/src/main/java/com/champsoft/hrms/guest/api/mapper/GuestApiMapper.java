package com.champsoft.hrms.guest.api.mapper;

import com.champsoft.hrms.guest.api.GuestResponse;
import com.champsoft.hrms.guest.domain.model.Guest;

public class GuestApiMapper {
    public static GuestResponse toResponse(Guest g) {
        return new GuestResponse(
                g.id().value(),
                g.fullName().value(),
                g.address().value(),
                g.status().name()
        );
    }
}