package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.UrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenRequest;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccessTokenFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private AccessTokenFetcher accessTokenFetcher;

    @BeforeEach
    void setUp() {
        accessTokenFetcher = new AccessTokenFetcher(
                fetcherSupport,
                new UrlProvider(),
                new ClientCredential("appKey", "appSecret")
        );
    }

    @Test
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
