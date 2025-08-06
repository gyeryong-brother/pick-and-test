package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import java.time.LocalDate;
import java.util.List;
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

    public String getStockPriceEndpoint(List<String> symbols, LocalDate startDate) {
        if (startDate == null) {
            return uriComponentsBuilder(symbols)
                    .queryParam("start", "1930-01-01")
                    .build()
                    .toUriString();
        }
        return uriComponentsBuilder(symbols)
                .queryParam("start", startDate.toString())
                .build()
                .toUriString();
    }

    private UriComponentsBuilder uriComponentsBuilder(List<String> symbols) {
        return UriComponentsBuilder.fromHttpUrl(domain)
                .path(STOCK_PRICE_ENDPOINT)
                .queryParam("tickers", joinedSymbols(symbols));
    }

    private String joinedSymbols(List<String> symbols) {
        return String.join(",", symbols);
    }

    public String getStockMinutePriceEndpoint(String symbol, LocalDate startDate) {
        if (startDate == null) {
            return minuteStockPrices(symbol)
                    .build()
                    .toUriString();
        }
        return minuteStockPrices(symbol)
                .queryParam("start", startDate.toString())
                .build()
                .toUriString();
    }

    private UriComponentsBuilder minuteStockPrices(String symbol) {
        return UriComponentsBuilder.fromHttpUrl(domain)
                .path(STOCK_PRICE_ENDPOINT)
                .queryParam("ticker", symbol)
                .queryParam("interval", "1m");
    }
}
