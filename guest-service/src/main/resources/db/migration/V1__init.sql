-- Flyway init schema for GUESTS SERVICE ONLY
-- This service owns the guests table

CREATE TABLE IF NOT EXISTS guests (
                                      id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
    );

INSERT INTO guests (id, full_name, address, status) VALUES
    ('guest-1', 'John Doe', '123 Main St, Montreal', 'ACTIVE'),
    ('guest-2', 'Jane Doe', '456 Queen St, Montreal', 'ACTIVE'),
    ('guest-3', 'Joanne Doe', '789 King St, Montreal', 'INACTIVE');