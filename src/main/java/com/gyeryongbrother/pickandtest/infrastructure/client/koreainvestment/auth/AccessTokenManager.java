package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.DateTimeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
