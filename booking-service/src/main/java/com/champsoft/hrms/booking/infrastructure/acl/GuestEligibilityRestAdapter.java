package com.champsoft.hrms.booking.infrastructure.acl;

import com.champsoft.hrms.booking.application.exception.CrossContextValidationException;
import com.champsoft.hrms.booking.application.port.out.GuestEligibilityPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GuestEligibilityRestAdapter implements GuestEligibilityPort {

    private final RestTemplate restTemplate;

    public GuestEligibilityRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isEligible(String guestId) {
        try {
            String url = "http://localhost:8080/api/guests/" + guestId + "/eligibility";
            Boolean result = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(result);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CrossContextValidationException("Guest not found: " + guestId);
        } catch (HttpClientErrorException ex) {
            throw new CrossContextValidationException("Guest validation failed: " + guestId);
        }
    }
}