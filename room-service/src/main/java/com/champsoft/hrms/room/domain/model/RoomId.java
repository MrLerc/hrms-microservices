package com.champsoft.hrms.room.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class RoomId {
    private final String value;

    private RoomId (String value) {
        this.value = value;
    }

    public static RoomId newId(){
        return new RoomId(UUID.randomUUID().toString());
    }

    public static RoomId of (String value){
        return new RoomId(value);
    }

    public String value() {return value;}

    @Override public boolean equals (Object o) {
        return (o instanceof RoomId other) && Objects.equals(value, other.value);
    }
    @Override public int hashCode () {return Objects.hash(value);}
    @Override public String toString () {return value;}
}