package com.champsoft.hrms.payment.api;

import com.champsoft.hrms.payment.api.mapper.PaymentApiMapper;
import com.champsoft.hrms.payment.application.service.PaymentCrudService;
import com.champsoft.hrms.payment.application.service.PaymentEligibilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentCrudService service;
    private final PaymentEligibilityService paymentEligibilityService;

    public PaymentController(PaymentCrudService service,
                             PaymentEligibilityService paymentEligibilityService) {
        this.service = service;
        this.paymentEligibilityService = paymentEligibilityService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreatePaymentRequest req) {
        var payment = service.create(
                req.cardNumber(),
                req.expiryDate(),
                req.amount(),
                req.type()
        );
        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        var payment = service.getById(id);
        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<?> getByCard(@PathVariable String cardNumber) {
        var payment = service.getByCardNumber(cardNumber);
        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        var data = service.list().stream()
                .map(PaymentApiMapper::toResponse)
                .toList();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody @Valid UpdatePaymentRequest req) {

        var payment = service.updateDetails(
                id,
                req.cardNumber(),
                req.expiryDate()
        );

        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable String id) {
        var payment = service.complete(id);
        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @PostMapping("/{id}/fail")
    public ResponseEntity<?> fail(@PathVariable String id) {
        var payment = service.fail(id);
        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable String id) {
        var payment = service.cancel(id);
        return ResponseEntity.ok(PaymentApiMapper.toResponse(payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/eligibility")
    public boolean isEligible(@PathVariable String id) {
        return paymentEligibilityService.isEligible(id);
    }
}