package com.champsoft.hrms.payment.domain.model;

import com.champsoft.hrms.payment.domain.exception.InvalidExpiryDateException;
import com.champsoft.hrms.payment.domain.exception.InvalidPaymentOperationException;
import com.champsoft.hrms.payment.domain.exception.PaymentAlreadyCompletedException;

public class Payment {

    private final PaymentId id;
    private double amount;
    private PaymentType type;

    private CardNumber cardNumber;
    private String expiryDate;

    private PaymentStatus status;

    public Payment(PaymentId id,
                   double amount,
                   PaymentType type,
                   CardNumber cardNumber,
                   String expiryDate) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (expiryDate == null || expiryDate.isBlank()) {
            throw new InvalidExpiryDateException("Expiry date is required");
        }

        if (!expiryDate.matches("(0[1-9]|1[0-2])\\/\\d{2}")) {
            throw new InvalidExpiryDateException("Expiry date must be in MM/YY format");
        }

        this.id = id;
        this.amount = amount;
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.status = PaymentStatus.PENDING;
    }

    // ===== Getters =====

    public PaymentId id() {
        return id;
    }

    public double amount() {
        return amount;
    }

    public PaymentType type() {
        return type;
    }

    public CardNumber cardNumber() {
        return cardNumber;
    }

    public String expiryDate() {
        return expiryDate;
    }

    public PaymentStatus status() {
        return status;
    }

    // ===== Domain behavior =====

    public void updatePaymentDetails(CardNumber newCardNumber, String newExpiryDate) {

        if (status != PaymentStatus.PENDING) {
            throw new InvalidPaymentOperationException(
                    "Cannot update payment details after processing starts"
            );
        }

        if (newExpiryDate == null || newExpiryDate.isBlank()) {
            throw new InvalidExpiryDateException("Expiry date is required");
        }

        if (!newExpiryDate.matches("(0[1-9]|1[0-2])\\/\\d{2}")) {
            throw new InvalidExpiryDateException("Expiry date must be in MM/YY format");
        }

        this.cardNumber = newCardNumber;
        this.expiryDate = newExpiryDate;
    }

    public void complete() {
        if (status == PaymentStatus.COMPLETED) {
            throw new PaymentAlreadyCompletedException("Payment already completed");
        }

        if (status == PaymentStatus.CANCELLED) {
            throw new InvalidPaymentOperationException("Cannot complete a cancelled payment");
        }

        this.status = PaymentStatus.COMPLETED;
    }

    public void fail() {
        if (status != PaymentStatus.PENDING) {
            throw new InvalidPaymentOperationException("Only PENDING payments can fail");
        }

        this.status = PaymentStatus.FAILED;
    }

    public void cancel() {
        if (status == PaymentStatus.COMPLETED) {
            throw new InvalidPaymentOperationException("Cannot cancel a completed payment");
        }

        this.status = PaymentStatus.CANCELLED;
    }

    // ===== Domain rule =====

    public boolean isEligibleForProcessing() {
        return status == PaymentStatus.PENDING;
    }
}