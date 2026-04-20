-- Flyway init schema for PAYMENTS SERVICE ONLY
-- This service owns the payments table

CREATE TABLE IF NOT EXISTS payments (
                                        id VARCHAR(36) PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    payment_type VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL
    );

INSERT INTO payments (id, amount, payment_type, status) VALUES
    ('payment-1', 100.0, 'CREDIT_CARD', 'PENDING'),
    ('payment-2', 150.0, 'DEBIT_CARD', 'COMPLETED'),
    ('payment-3', 250.0, 'CREDIT_CARD', 'PENDING');