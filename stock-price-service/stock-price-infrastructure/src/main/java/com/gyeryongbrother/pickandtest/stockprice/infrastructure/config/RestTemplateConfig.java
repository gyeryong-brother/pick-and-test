package com.gyeryongbrother.pickandtest.stockprice.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateBuilder restTemplateBuilder;
    private final ResponseErrorHandler responseErrorHandler;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder
                .errorHandler(responseErrorHandler)
                .build();
    }
}
