-- Flyway init schema for ROOMS SERVICE ONLY
-- This service owns the rooms table

CREATE TABLE IF NOT EXISTS rooms (
                                     id VARCHAR(36) PRIMARY KEY,
    room_number VARCHAR(20) NOT NULL UNIQUE,
    room_type VARCHAR(20) NOT NULL,
    price_per_night DOUBLE PRECISION NOT NULL,
    status VARCHAR(20) NOT NULL
    );

INSERT INTO rooms (id, room_number, room_type, price_per_night, status) VALUES
    ('room-1', '101', 'SINGLE', 100.0, 'AVAILABLE'),
    ('room-2', '102', 'DOUBLE', 150.0, 'AVAILABLE'),
    ('room-3', '103', 'SUITE', 250.0, 'OCCUPIED');