package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("토큰 시간을 관리한다")
class AccessTokenTest {

    private AccessToken accessToken;

    @BeforeEach
    void setUp() {
        accessToken = new AccessToken();
    }

    @Test
    @DisplayName("초기 상태는 만료이다")
    void isExpiredTrue() {
        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("만료시간이 과거이면 만료이다")
    void isExpiredToken() {
        // given
        accessToken.update("accessToken", LocalDateTime.MIN);

        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("만료 시간이 미래면 만료가 아니다")
    void isExpiredFalse() {
        // given
        accessToken.update("accessToken", LocalDateTime.MAX);

        // when
        boolean result = accessToken.isExpired();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("액세스 토큰과 만료 시간을 업데이트한다")
    void update() {
        // when & then
        assertThatCode(() -> accessToken.update("accessToken", LocalDateTime.now()))
                .doesNotThrowAnyException();
    }
}
