package com.champsoft.hrms.booking.infrastructure.acl;

import com.champsoft.hrms.booking.application.exception.CrossContextValidationException;
import com.champsoft.hrms.booking.application.port.out.PaymentEligibilityPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentEligibilityRestAdapter implements PaymentEligibilityPort {

    private final RestTemplate restTemplate;

    @Value("https://localhost:8083")
    private String paymentsBaseUrl;

    public PaymentEligibilityRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isEligible(String paymentId) {
        String url = paymentsBaseUrl + "/api/payments/" + paymentId + "/eligibility";

        try {
            Boolean result = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(result);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CrossContextValidationException("Payment not found: " + paymentId);
        } catch (HttpClientErrorException ex) {
            throw new CrossContextValidationException("Payment validation failed: " + paymentId);
        } catch (Exception ex) {
            throw new CrossContextValidationException("Payment service is unavailable.");
        }
    }
}