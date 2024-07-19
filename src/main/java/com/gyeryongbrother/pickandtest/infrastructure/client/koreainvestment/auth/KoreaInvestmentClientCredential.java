package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KoreaInvestmentClientCredential {

    private final String appKey;
    private final String appSecret;

    public KoreaInvestmentClientCredential(
            @Value("${korea-investment-securities.app-key}") String appKey,
            @Value("${korea-investment-securities.app-secret}") String appSecret
    ) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
}
