package com.champsoft.hrms.payment.application.service;

import com.champsoft.hrms.payment.application.exception.PaymentNotFoundException;
import com.champsoft.hrms.payment.application.port.out.PaymentRepositoryPort;
import com.champsoft.hrms.payment.domain.model.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentCrudService {

    private final PaymentRepositoryPort repo;

    public PaymentCrudService(PaymentRepositoryPort repo) {
        this.repo = repo;
    }

    @Transactional
    public Payment create(String cardNumber, String expiryDate, double amount, PaymentType type) {

        var card = new CardNumber(cardNumber);

        if (repo.existsByCardNumber(card)) {
            throw new IllegalArgumentException("Card number already exists");
        }

        var payment = new Payment(
                PaymentId.newId(),
                amount,
                type,
                card,
                expiryDate
        );

        return repo.save(payment);
    }

    @Transactional(readOnly = true)
    public Payment getById(String id) {
        return repo.findById(PaymentId.of(id))
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found: " + id));
    }

    @Transactional(readOnly = true)
    public Payment getByCardNumber(String cardNumber) {
        return repo.findByCardNumber(new CardNumber(cardNumber))
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for card: " + cardNumber));
    }

    @Transactional(readOnly = true)
    public List<Payment> list() {
        return repo.findAll();
    }

    @Transactional
    public Payment updateDetails(String id, String cardNumber, String expiryDate) {

        var payment = getById(id);

        payment.updatePaymentDetails(
                new CardNumber(cardNumber),
                expiryDate
        );

        return repo.save(payment);
    }

    @Transactional
    public Payment complete(String id) {
        var payment = getById(id);
        payment.complete();
        return repo.save(payment);
    }

    @Transactional
    public Payment fail(String id) {
        var payment = getById(id);
        payment.fail();
        return repo.save(payment);
    }

    @Transactional
    public Payment cancel(String id) {
        var payment = getById(id);
        payment.cancel();
        return repo.save(payment);
    }

    @Transactional
    public void delete(String id) {
        getById(id);
        repo.deleteById(PaymentId.of(id));
    }
}