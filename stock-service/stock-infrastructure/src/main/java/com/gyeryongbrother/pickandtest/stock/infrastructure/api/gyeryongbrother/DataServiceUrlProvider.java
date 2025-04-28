package com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DataServiceUrlProvider {

    private static final String STOCK_ENDPOINT = "/stocks/%s";
    private static final String TICKERS_ENDPOINT = "/tickers";

    private final String domain;

    public DataServiceUrlProvider(
            @Value("${data-service.url}") String domain
    ) {
        this.domain = domain;
    }

    public String getStockEndpoint(String symbol) {
        return UriComponentsBuilder.fromHttpUrl(domain)
                .path(String.format(STOCK_ENDPOINT, symbol))
                .build()
                .toUriString();
    }

    public String getTickersEndpoint(String exchange) {
        return UriComponentsBuilder.fromHttpUrl(domain)
                .path(TICKERS_ENDPOINT)
                .queryParam("exchange", exchange)
                .build()
                .toUriString();
    }
}
