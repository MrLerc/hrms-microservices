package com.champsoft.hrms.payment.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGroupsConfig {

    @Bean
    GroupedOpenApi paymentsApi() {
        return GroupedOpenApi.builder()
                .group("payments")
                .pathsToMatch("/api/payments/**")
                .build();
    }
}