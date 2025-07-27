package com.gyeryongbrother.pickandtest.gateway.security;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder.withSecretKey;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

@Configuration
public class JwtDecoderConfig {

    private static final String ALGORITHM = "HmacSHA256";

    private final String secretKey;

    public JwtDecoderConfig(
            @Value("${jwt.secret-key}") String secretKey
    ) {
        this.secretKey = secretKey;
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] bytes = secretKey.getBytes(UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(bytes, ALGORITHM);
        return withSecretKey(keySpec).build();
    }
}
