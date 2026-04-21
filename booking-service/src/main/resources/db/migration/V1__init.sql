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

-- Helpful indexes for performance
CREATE INDEX IF NOT EXISTS idx_bookings_room_id ON bookings(room_id);
CREATE INDEX IF NOT EXISTS idx_bookings_guest_id ON bookings(guest_id);
CREATE INDEX IF NOT EXISTS idx_bookings_payment_id ON bookings(payment_id);

INSERT INTO bookings (id, room_id, guest_id, payment_id, start_date, end_date, status) VALUES
                                                                                           ('book-1', 'room-1', 'guest-1', 'pay-1', DATE '2026-05-01', DATE '2026-05-05', 'CONFIRMED'),
                                                                                           ('book-2', 'room-2', 'guest-2', 'pay-2', DATE '2026-06-10', DATE '2026-06-15', 'CONFIRMED'),
                                                                                           ('book-3', 'room-3', 'guest-3', 'pay-3', DATE '2026-07-01', DATE '2026-07-03', 'CREATED'),
                                                                                           ('book-4', 'room-4', 'guest-4', 'pay-4', DATE '2026-08-12', DATE '2026-08-18', 'CONFIRMED'),
                                                                                           ('book-5', 'room-5', 'guest-5', 'pay-5', DATE '2026-09-20', DATE '2026-09-25', 'CREATED');