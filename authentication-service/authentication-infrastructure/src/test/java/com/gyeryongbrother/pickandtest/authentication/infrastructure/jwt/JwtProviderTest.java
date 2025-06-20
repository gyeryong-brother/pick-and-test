package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

    @Test
    @DisplayName("AccessToken을 생성한다")
    void generateAccessToken() {
        JwtProvider jwtProvider = new JwtProvider("abc");
        String accessToken = jwtProvider.generateAccessToken(tokenizable());
        assertThat(accessToken).isNotNull();
    }

    private Tokenizable tokenizable() {
        return new Tokenizable() {
            @Override
            public String subject() {
                return "1";
            }

            @Override
            public List<String> authorities() {
                return List.of("USER");
            }
        };
    }

    @Test
    @DisplayName("refreshToken을 생성한다")
    void generateRefreshToken() {
        JwtProvider jwtProvider = new JwtProvider("abc");
        String refreshToken = jwtProvider.generateRefreshToken(tokenizable());
        assertThat(refreshToken).isNotNull();
    }
}
