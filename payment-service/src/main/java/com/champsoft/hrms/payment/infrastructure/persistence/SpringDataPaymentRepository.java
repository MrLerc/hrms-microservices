package com.champsoft.hrms.payment.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataPaymentRepository
        extends JpaRepository<PaymentJpaEntity, String> {

    Optional<PaymentJpaEntity> findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);
}