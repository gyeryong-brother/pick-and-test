package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

    @Test
    @DisplayName("AccessToken을 생성한다")
    void generateAccessToken() {
        JwtProvider jwtProvider = new JwtProvider();
        String accessToken = jwtProvider.generateAccessToken("1", List.of("USER"));
        assertThat(accessToken).isNotNull();
    }

    @Test
    @DisplayName("refreshToken을 생성한다")
    void generateRefreshToken() {
        JwtProvider jwtProvider = new JwtProvider();
        String refreshToken = jwtProvider.generateRefreshToken("1", List.of("USER"));
        assertThat(refreshToken).isNotNull();
    }
}
