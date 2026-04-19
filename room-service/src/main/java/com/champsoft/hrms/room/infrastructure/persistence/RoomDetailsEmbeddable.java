package com.champsoft.hrms.room.infrastructure.persistence;

import com.champsoft.hrms.room.domain.model.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class RoomDetailsEmbeddable {

    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public RoomType roomType;

    @Column(name = "price_per_night", nullable = false)
    public double pricePerNight;

    protected RoomDetailsEmbeddable() {}

    public RoomDetailsEmbeddable(RoomType roomType, double pricePerNight) {
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }
}