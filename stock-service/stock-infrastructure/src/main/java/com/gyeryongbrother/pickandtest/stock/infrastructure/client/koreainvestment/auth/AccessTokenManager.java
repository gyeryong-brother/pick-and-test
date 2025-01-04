package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.auth;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.DateTimeHandler;
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
        TokenResponse tokenResponse = accessTokenFetcher.fetchToken();
        String accessTokenExpired = tokenResponse.accessTokenExpired();
        LocalDateTime expiresAt = DateTimeHandler.toDateTime(accessTokenExpired);
        accessToken.update(tokenResponse.accessToken(), expiresAt);
    }
}
