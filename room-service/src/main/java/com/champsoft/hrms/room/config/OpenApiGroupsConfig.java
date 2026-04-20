package com.champsoft.hrms.room.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGroupsConfig {

    @Bean
    GroupedOpenApi roomsApi() {
        return GroupedOpenApi.builder()
                .group("rooms")
                .pathsToMatch("/api/rooms/**")
                .build();
    }
}