package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.common;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class StockServiceUrlProvider {

    private static final String DOMAIN = "http://localhost:8080";
    private static final String STOCK_ENDPOINT = "/stocks/%s";

    public String getStockEndpoint(Long stockId) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(String.format(STOCK_ENDPOINT, stockId))
                .build()
                .toUriString();
    }
}
