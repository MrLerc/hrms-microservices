package com.champsoft.hrms.room.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class RoomJpaEntity {
    @Id
    public String id;

    @Column(name = "room_number", nullable = false, unique = true)
    public String roomNumber;

    @Embedded
    public RoomDetailsEmbeddable specs;

    @Column(nullable = false)
    public String status;
}
