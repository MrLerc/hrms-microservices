package com.champsoft.hrms.booking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookingServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Booking Service API")
                        .version("1.0.0")
                        .description("Booking microservice for the Hotel Reservation Management System")
                        .contact(new Contact().name("HRMS Team").email("n/a"))
                        .license(new License().name("Apache 2.0")));
    }
}