package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.KoreaInvestmentUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenRequest;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AccessTokenFetcher {

    private final FetcherSupport fetcherSupport;
    private final KoreaInvestmentUrlProvider koreaInvestmentUrlProvider;
    private final KoreaInvestmentClientCredential koreaInvestmentClientCredential;

    public TokenResponse fetchToken() {
        String tokenUrl = koreaInvestmentUrlProvider.getTokenEndpoint();
        TokenRequest tokenRequest = createTokenRequest();
        return fetcherSupport.post(tokenUrl, tokenRequest, TokenResponse.class);
    }

    private TokenRequest createTokenRequest() {
        return new TokenRequest(
                "client_credentials",
                koreaInvestmentClientCredential.getAppKey(),
                koreaInvestmentClientCredential.getAppSecret()
        );
    }
}
