package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String alreadyExpired = LocalDateTime.now().format(dateTimeFormatter);
        accessToken.update("accessToken", alreadyExpired);

        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void isExpiredFalse() {
        // given
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String neverExpired = LocalDateTime.MAX.format(dateTimeFormatter);
        accessToken.update("accessToken", neverExpired);

        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isFalse();
    }

    @Test
    void update() {
        // given
        String actualExpiredFormat = "2024-07-14 14:44:10";

        // when & then
        assertThatCode(() -> accessToken.update("accessToken", actualExpiredFormat))
                .doesNotThrowAnyException();
    }
}
