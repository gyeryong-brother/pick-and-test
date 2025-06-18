package com.gyeryongbrother.pickandtest.gateway.security;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder.withSecretKey;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

@Configuration
public class JwtDecoderConfig {

    private static final String SECRET_KEY = "my-secret-key-for-the-project-pickandtest";
    private static final String ALGORITHM = "HmacSHA256";

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] bytes = SECRET_KEY.getBytes(UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(bytes, ALGORITHM);
        return withSecretKey(keySpec).build();
    }
}
