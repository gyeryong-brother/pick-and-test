package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StockServiceUrlProvider {

    private static final String STOCK_ENDPOINT = "/stock-service/stocks/%s";

    private final String domain;

    public StockServiceUrlProvider(
            @Value("${stock-service.url}") String domain
    ) {
        this.domain = domain;
    }

    public String getStockEndpoint(Long stockId) {
        return UriComponentsBuilder.fromHttpUrl(domain)
                .path(String.format(STOCK_ENDPOINT, stockId))
                .build()
                .toUriString();
    }
}
