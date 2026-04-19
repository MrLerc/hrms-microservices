package com.champsoft.hrms.payment.application.port.out;

import com.champsoft.hrms.payment.domain.model.CardNumber;
import com.champsoft.hrms.payment.domain.model.Payment;
import com.champsoft.hrms.payment.domain.model.PaymentId;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {

    Payment save(Payment payment);

    Optional<Payment> findById(PaymentId id);

    Optional<Payment> findByCardNumber(CardNumber cardNumber);

    boolean existsByCardNumber(CardNumber cardNumber);

    List<Payment> findAll();

    void deleteById(PaymentId id);
}