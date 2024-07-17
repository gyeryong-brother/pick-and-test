package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AlphaVantageClientCredential {

    private final String apiKey;

    public AlphaVantageClientCredential(@Value("${alpha-vantage.api-key}") String apiKey) {
        this.apiKey = apiKey;
    }
}
