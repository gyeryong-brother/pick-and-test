package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class AlphaVantageUrlProvider {

    private static final String DOMAIN = "https://www.alphavantage.co";
    private static final String DIVIDEND_ENDPOINT = "/query";
    private static final String FUNCTION_PARAM_NAME = "function";
    private static final String DIVIDEND_FUNCTION_PARAM_VALUE = "DIVIDENDS";
    private static final String SYMBOL_PARAM_NAME = "symbol";
    private static final String API_KEY_PARAM_NAME = "apikey";

    private final AlphaVantageClientCredential alphaVantageClientCredential;

    public String getDividendEndpoint(String symbol) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(DIVIDEND_ENDPOINT)
                .queryParam(FUNCTION_PARAM_NAME, DIVIDEND_FUNCTION_PARAM_VALUE)
                .queryParam(SYMBOL_PARAM_NAME, symbol)
                .queryParam(API_KEY_PARAM_NAME, alphaVantageClientCredential.getApiKey())
                .build()
                .toUriString();
    }
}
