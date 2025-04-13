package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.auth;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.auth.dto.TokenResponse;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.DateTimeHandler;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AccessTokenManager {

    private final AccessTokenFetcher accessTokenFetcher;
    private final AccessToken accessToken;

    public String getAccessToken() {
        if (accessToken.isExpired()) {
            fetchAccessToken();
        }
        return accessToken.getValue();
    }

    private void fetchAccessToken() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        TokenResponse tokenResponse = accessTokenFetcher.fetchToken();
        String accessTokenExpired = tokenResponse.accessTokenExpired();
        LocalDateTime expiresAt = DateTimeHandler.toDateTime(accessTokenExpired);
        accessToken.update(tokenResponse.accessToken(), expiresAt);
    }
}
