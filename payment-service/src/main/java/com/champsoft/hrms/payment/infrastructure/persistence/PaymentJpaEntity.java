package com.champsoft.hrms.payment.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class PaymentJpaEntity {

    @Id
    public String id;

    @Column(nullable = false)
    public double amount;

    @Column(nullable = false)
    public String type;

    @Column(name = "card_number", nullable = false)
    public String cardNumber;

    @Column(name = "expiry_date", nullable = false)
    public String expiryDate;

    @Column(nullable = false)
    public String status;
}