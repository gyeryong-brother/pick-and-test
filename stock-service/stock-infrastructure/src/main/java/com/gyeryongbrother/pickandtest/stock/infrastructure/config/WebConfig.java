package com.gyeryongbrother.pickandtest.stock.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String LOCAL_SERVER_URL = "http://localhost:3000";
    private static final String DEV_SERVER_URL = "http://a5f233ca353c94a4db24fc2debccefc7-1603964126.ap-northeast-2.elb.amazonaws.com";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(LOCAL_SERVER_URL, DEV_SERVER_URL)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
