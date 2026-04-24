package com.champsoft.hrms.booking.infrastructure.acl;

import com.champsoft.hrms.booking.application.exception.CrossContextValidationException;
import com.champsoft.hrms.booking.application.port.out.GuestEligibilityPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class GuestEligibilityRestAdapter implements GuestEligibilityPort {

    private final RestTemplate restTemplate;

    @Value("${services.guests.base-url}")
    private String guestsBaseUrl;

    public GuestEligibilityRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isEligible(String guestId) {
        String url = guestsBaseUrl + "/api/guests/" + guestId + "/eligibility";

        try {
            Boolean result = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(result);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CrossContextValidationException("Guest not found: " + guestId);
        } catch (HttpClientErrorException ex) {
            throw new CrossContextValidationException("Guest validation failed: " + guestId);
        } catch (Exception ex) {
            throw new CrossContextValidationException("Guest service is unavailable.");
        }
    }
}