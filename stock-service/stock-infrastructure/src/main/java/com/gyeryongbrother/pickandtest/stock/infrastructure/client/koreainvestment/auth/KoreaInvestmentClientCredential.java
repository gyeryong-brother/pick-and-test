package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
class KoreaInvestmentClientCredential {

    private final String appKey;
    private final String appSecret;

    public KoreaInvestmentClientCredential(
            @Value("${korea-investment.app-key}") String appKey,
            @Value("${korea-investment.app-secret}") String appSecret
    ) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
}
