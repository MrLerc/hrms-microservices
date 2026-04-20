package com.champsoft.hrms.guest.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGroupsConfig {

    @Bean
    GroupedOpenApi guestsApi() {
        return GroupedOpenApi.builder()
                .group("guests")
                .pathsToMatch("/api/guests/**")
                .build();
    }
}