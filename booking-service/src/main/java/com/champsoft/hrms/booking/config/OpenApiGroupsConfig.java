package com.champsoft.hrms.booking.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGroupsConfig {

    @Bean
    GroupedOpenApi bookingsApi() {
        return GroupedOpenApi.builder()
                .group("bookings")
                .pathsToMatch("/api/bookings/**")
                .build();
    }
}