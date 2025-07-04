package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.Tokenizable;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

    @Test
    @DisplayName("AccessToken을 생성한다")
    void generateAccessToken() {
        JwtProvider jwtProvider = new JwtProvider("2ee17b50c408d0c4888e10c514d8cee5b710f1f92e397452777b12fb01fd89e3");
        String accessToken = jwtProvider.generateAccessToken(tokenizable());
        assertThat(accessToken).isNotNull();
    }

    private Tokenizable tokenizable() {
        return new Tokenizable() {
            @Override
            public LoginType type() {
                return null;
            }

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
        JwtProvider jwtProvider = new JwtProvider("2ee17b50c408d0c4888e10c514d8cee5b710f1f92e397452777b12fb01fd89e3");
        String refreshToken = jwtProvider.generateRefreshToken(tokenizable());
        assertThat(refreshToken).isNotNull();
    }
}
