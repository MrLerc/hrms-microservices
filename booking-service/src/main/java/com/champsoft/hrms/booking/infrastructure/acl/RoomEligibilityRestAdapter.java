package com.champsoft.hrms.booking.infrastructure.acl;

import com.champsoft.hrms.booking.application.exception.CrossContextValidationException;
import com.champsoft.hrms.booking.application.port.out.RoomEligibilityPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RoomEligibilityRestAdapter implements RoomEligibilityPort {

    private final RestTemplate restTemplate;

    @Value("https://localhost:8082")
    private String roomsBaseUrl;

    public RoomEligibilityRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isEligible(String roomId) {
        String url = roomsBaseUrl + "/api/rooms/" + roomId + "/eligibility";

        try {
            Boolean result = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(result);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CrossContextValidationException("Room not found: " + roomId);
        } catch (HttpClientErrorException ex) {
            throw new CrossContextValidationException("Room validation failed: " + roomId);
        } catch (Exception ex) {
            throw new CrossContextValidationException("Room service is unavailable.");
        }
    }
}