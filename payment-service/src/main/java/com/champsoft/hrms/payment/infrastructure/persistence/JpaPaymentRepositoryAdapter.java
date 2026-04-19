package com.champsoft.hrms.payment.infrastructure.persistence;

import com.champsoft.hrms.payment.application.port.out.PaymentRepositoryPort;
import com.champsoft.hrms.payment.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaPaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final SpringDataPaymentRepository jpa;

    public JpaPaymentRepositoryAdapter(SpringDataPaymentRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Payment save(Payment payment) {
        jpa.save(toEntity(payment));
        return payment;
    }

    @Override
    public Optional<Payment> findById(PaymentId id) {
        return jpa.findById(id.value()).map(this::toDomain);
    }

    @Override
    public Optional<Payment> findByCardNumber(CardNumber cardNumber) {
        return jpa.findByCardNumber(cardNumber.value())
                .map(this::toDomain);
    }

    @Override
    public boolean existsByCardNumber(CardNumber cardNumber) {
        return jpa.existsByCardNumber(cardNumber.value());
    }

    @Override
    public List<Payment> findAll() {
        return jpa.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(PaymentId id) {
        jpa.deleteById(id.value());
    }

    // ================= MAPPING =================

    private Payment toDomain(PaymentJpaEntity e) {
        return new Payment(
                PaymentId.of(e.id),
                e.amount,
                PaymentType.valueOf(e.type),
                new CardNumber(e.cardNumber),
                e.expiryDate
        );
    }

    private PaymentJpaEntity toEntity(Payment p) {
        var e = new PaymentJpaEntity();

        e.id = p.id().value();
        e.amount = p.amount();
        e.type = p.type().name();
        e.cardNumber = p.cardNumber().value();
        e.expiryDate = p.expiryDate();

        e.status = p.status().name();

        return e;
    }
}