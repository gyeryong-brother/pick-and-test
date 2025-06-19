package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

class JwtProviderTest {

    @Test
    @DisplayName("AccessToken을 생성한다")
    void generateAccessToken() {
        OAuth2User oAuth2User = new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("USER")),
                Map.of("id", "1"),
                "id"
        );
        JwtProvider jwtProvider = new JwtProvider("abc");
        String accessToken = jwtProvider.generateAccessToken(oAuth2User);
        assertThat(accessToken).isNotNull();
    }

    @Test
    @DisplayName("refreshToken을 생성한다")
    void generateRefreshToken() {
        OAuth2User oAuth2User = new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("USER")),
                Map.of("id", "1"),
                "id"
        );
        JwtProvider jwtProvider = new JwtProvider("abc");
        String refreshToken = jwtProvider.generateRefreshToken(oAuth2User);
        assertThat(refreshToken).isNotNull();
    }
}
