-- Flyway init schema for PAYMENTS SERVICE ONLY
-- This service owns the payments table

CREATE TABLE IF NOT EXISTS payments (
                                        id VARCHAR(36) PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    type VARCHAR(20) NOT NULL,
    card_number VARCHAR(32) NOT NULL,
    expiry_date VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL
    );

INSERT INTO payments (id, amount, type, card_number, expiry_date, status) VALUES
                                                                              ('pay-1', 200.0, 'DEBIT', '4111151111111111', '12/26', 'COMPLETED'),
                                                                              ('pay-2', 300.0, 'DEBIT', '4111161111111111', '11/26', 'COMPLETED'),
                                                                              ('pay-3', 150.0, 'DEBIT', '4111171111111111', '10/26', 'PENDING'),
                                                                              ('pay-4', 500.0, 'CREDIT', '4111181111111111', '09/26', 'COMPLETED'),
                                                                              ('pay-5', 220.0, 'CREDIT', '4111191111111111', '08/26', 'COMPLETED');
