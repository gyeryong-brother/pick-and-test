package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DataServiceUrlProvider {

    private static final String STOCK_PRICE_ENDPOINT = "/stock-prices";

    private final String domain;

    public DataServiceUrlProvider(
            @Value("${data-service.url}") String domain
    ) {
        this.domain = domain;
    }

    public String getStockPriceEndpoint(String symbol, LocalDate startDate) {
        if (startDate == null) {
            return uriComponentsBuilder(symbol)
                    .build()
                    .toUriString();
        }
        return uriComponentsBuilder(symbol)
                .queryParam("start", startDate.toString())
                .build()
                .toUriString();
    }

    private UriComponentsBuilder uriComponentsBuilder(String symbol) {
        return UriComponentsBuilder.fromHttpUrl(domain)
                .path(STOCK_PRICE_ENDPOINT)
                .queryParam("ticker", symbol);
    }
}
