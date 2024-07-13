package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.dto.TokenResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessTokenManager {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private final AccessTokenFetcher accessTokenFetcher;
    private String accessToken;
    private LocalDateTime expiresAt;

    public String getAccessToken() {
        if (isExpired()) {
            fetchAccessToken();
        }
        return accessToken;
    }

    private boolean isExpired() {
        if (expiresAt == null) {
            return true;
        }
        LocalDateTime now = LocalDateTime.now();
        return expiresAt.isBefore(now);
    }

    private void fetchAccessToken() {
        TokenResponse tokenResponse = accessTokenFetcher.fetchToken();
        accessToken = tokenResponse.accessToken();
        expiresAt = parse(tokenResponse.accessTokenExpired());
    }

    private LocalDateTime parse(String accessTokenExpired) {
        return LocalDateTime.parse(accessTokenExpired, DATE_TIME_FORMATTER);
    }
}
