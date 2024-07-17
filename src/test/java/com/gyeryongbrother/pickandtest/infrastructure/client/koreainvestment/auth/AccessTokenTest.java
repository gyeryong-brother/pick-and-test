package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccessTokenTest {

    private AccessToken accessToken;

    @BeforeEach
    void setUp() {
        accessToken = new AccessToken();
    }

    @Test
    void isExpiredTrue() {
        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void isExpiredToken() {
        // given
        accessToken.update("accessToken", LocalDateTime.now());

        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void isExpiredFalse() {
        // given
        accessToken.update("accessToken", LocalDateTime.MAX);

        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void update() {
        // when & then
        assertThatCode(() -> accessToken.update("accessToken", LocalDateTime.now()))
                .doesNotThrowAnyException();
    }
}
