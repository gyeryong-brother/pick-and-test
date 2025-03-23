package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.auth;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
class AccessToken {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private String value;
    private LocalDateTime expiresAt;

    public boolean isExpired() {
        if (expiresAt == null) {
            return true;
        }
        LocalDateTime now = LocalDateTime.now();
        return expiresAt.isBefore(now);
    }

    public void update(String accessToken, LocalDateTime expiresAt) {
        value = accessToken;
        this.expiresAt = expiresAt;
    }
}
