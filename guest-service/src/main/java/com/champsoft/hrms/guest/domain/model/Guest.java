package com.champsoft.hrms.guest.domain.model;

public class Guest {
    private final GuestId id;
    private FullName fullName;
    private Address address;
    private GuestStatus status;

    public Guest(GuestId id, FullName fullName, Address address) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.status = GuestStatus.INACTIVE;
    }

    public GuestId id() { return id; }
    public FullName fullName() { return fullName; }
    public Address address() { return address; }
    public GuestStatus status() { return status; }

    public void update(FullName name, Address address) {
        this.fullName = name;
        this.address = address;
    }

    public void activate() {
        this.status = GuestStatus.ACTIVE;
    }

    public void suspend() {
        this.status = GuestStatus.SUSPENDED;
    }

    public boolean isEligibleForRegistration() {
        return status == GuestStatus.ACTIVE;
    }
}