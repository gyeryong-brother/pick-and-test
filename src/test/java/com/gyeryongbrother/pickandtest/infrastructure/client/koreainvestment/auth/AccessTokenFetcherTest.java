package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenRequest;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.KoreaInvestmentUrlProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("액세스 토큰을 가져온다")
class AccessTokenFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private AccessTokenFetcher accessTokenFetcher;

    @BeforeEach
    void setUp() {
        accessTokenFetcher = new AccessTokenFetcher(
                fetcherSupport,
                new KoreaInvestmentUrlProvider(),
                new KoreaInvestmentClientCredential("appKey", "appSecret")
        );
    }

    @Test
    @DisplayName("액세스 토큰을 가져온다")
    void fetchToken() {
        // given
        TokenResponse tokenResponse = new TokenResponse("accessToken", "accessTokenExpired");
        given(fetcherSupport.post(anyString(), any(TokenRequest.class), any()))
                .willReturn(tokenResponse);

        // when
        TokenResponse result = accessTokenFetcher.fetchToken();

        // then
        assertThat(result).isEqualTo(tokenResponse);
    }
}
