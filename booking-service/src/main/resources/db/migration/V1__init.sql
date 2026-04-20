-- Flyway init schema for BOOKING SERVICE ONLY
-- This service owns the bookings table only.
-- room_id, guest_id, and payment_id are external references to other services.
-- No cross-service foreign keys are used.

CREATE TABLE IF NOT EXISTS bookings (
                                        id VARCHAR(36) PRIMARY KEY,
    room_id VARCHAR(36) NOT NULL,
    guest_id VARCHAR(36) NOT NULL,
    payment_id VARCHAR(36) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL
    );

CREATE INDEX IF NOT EXISTS idx_bookings_room_id ON bookings(room_id);
CREATE INDEX IF NOT EXISTS idx_bookings_guest_id ON bookings(guest_id);
CREATE INDEX IF NOT EXISTS idx_bookings_payment_id ON bookings(payment_id);

INSERT INTO bookings (id, room_id, guest_id, payment_id, start_date, end_date, status) VALUES
    ('book-1', 'room-1', 'guest-1', 'payment-1', DATE '2026-05-01', DATE '2026-05-03', 'CREATED'),
    ('book-2', 'room-2', 'guest-2', 'payment-2', DATE '2026-05-04', DATE '2026-05-06', 'CREATED'),
    ('book-3', 'room-3', 'guest-3', 'payment-3', DATE '2026-05-07', DATE '2026-05-09', 'CANCELLED');