-- Flyway init schema for GUESTS SERVICE ONLY
-- This service owns the guests table

CREATE TABLE IF NOT EXISTS guests (
                                      id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(120) NOT NULL,
    address VARCHAR(255),
    status VARCHAR(20) NOT NULL
    );

INSERT INTO guests (id, full_name, address, status) VALUES
                                                        ('guest-1', 'John Smith', 'Brossard, QC', 'ACTIVE'),
                                                        ('guest-2', 'Sarah Johnson', 'Longueuil, QC', 'ACTIVE'),
                                                        ('guest-3', 'Michael Brown', 'Brossard, QC', 'ACTIVE'),
                                                        ('guest-4', 'Emily Davis', 'Montreal, QC', 'ACTIVE'),
                                                        ('guest-5', 'David Wilson', 'Longueuil, QC', 'ACTIVE');
