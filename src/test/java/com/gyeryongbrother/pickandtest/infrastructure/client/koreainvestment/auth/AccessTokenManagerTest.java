package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("액세스 토큰을 관리한다")
class AccessTokenManagerTest {

    @Mock
    private AccessTokenFetcher accessTokenFetcher;

    private AccessTokenManager accessTokenManager;

    @BeforeEach
    void setUp() {
        accessTokenManager = new AccessTokenManager(accessTokenFetcher, new AccessToken());
    }

    @Test
    @DisplayName("초기 상태에서는 새 토큰을 받아와서 반환한다")
    void getAccessToken() {
        // given
        String actualExpiredFormat = "2024-07-14 14:44:10";
        TokenResponse tokenResponse = new TokenResponse("accessToken", actualExpiredFormat);
        given(accessTokenFetcher.fetchToken())
                .willReturn(tokenResponse);

        // when
        String result = accessTokenManager.getAccessToken();

        // then
        assertThat(result).isEqualTo("accessToken");
    }

    @Test
    @DisplayName("토큰이 만료되지 않았으면 기존 토큰을 반환한다")
    void getAccessTokenTwice() {
        // given
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String neverExpired = LocalDateTime.MAX.format(dateTimeFormatter);
        TokenResponse tokenResponse = new TokenResponse("accessToken", neverExpired);
        given(accessTokenFetcher.fetchToken())
                .willReturn(tokenResponse);
        accessTokenManager.getAccessToken();

        // when
        String result = accessTokenManager.getAccessToken();

        // then
        assertThat(result).isEqualTo("accessToken");
        verify(accessTokenFetcher, times(1)).fetchToken();
    }

    @Test
    @DisplayName("기존 토큰이 만료되었으면 새 토큰을 받아와서 반환한다")
    void renewalAccessToken() {
        // given
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String alreadyExpired = LocalDateTime.now()
                .minusDays(1)
                .format(dateTimeFormatter);
        TokenResponse firstTokenResponse = new TokenResponse("accessToken", alreadyExpired);
        TokenResponse secondTokenResponse = new TokenResponse("renewalAccessToken", alreadyExpired);
        given(accessTokenFetcher.fetchToken())
                .willReturn(firstTokenResponse, secondTokenResponse);
        accessTokenManager.getAccessToken();

        // when
        String result = accessTokenManager.getAccessToken();

        // then
        assertThat(result).isEqualTo("renewalAccessToken");
        verify(accessTokenFetcher, times(2)).fetchToken();
    }
}
