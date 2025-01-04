package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NasdaqUrlProvider {

    private static final String DOMAIN = "https://api.nasdaq.com";
    private static final String STOCK_EXCHANGE_ENDPOINT = "/api/screener/stocks";
    private static final String TABLE_ONLY_PARAM_NAME = "tableonly";
    private static final String TABLE_ONLY_PARAM_VALUE = "true";
    private static final String LIMIT_PARAM_NAME = "limit";
    private static final String LIMIT_PARAM_VALUE = "5000";
    private static final String EXCHANGE_PARAM_NAME = "exchange";
    private static final String DIVIDEND_ENDPOINT = "/api/quote/%s/dividends";
    private static final String ASSET_CLASS_PARAM_NAME = "assetclass";

    public String getStockSymbolEndpoint(StockExchange stockExchange) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(STOCK_EXCHANGE_ENDPOINT)
                .queryParam(TABLE_ONLY_PARAM_NAME, TABLE_ONLY_PARAM_VALUE)
                .queryParam(LIMIT_PARAM_NAME, LIMIT_PARAM_VALUE)
                .queryParam(EXCHANGE_PARAM_NAME, stockExchange.name())
                .build()
                .toUriString();
    }

    public URI getDividendEndpoint(String symbol, String assetClass) {
        String encodedSymbol = symbol.replace("/", "%25sl%25");
        String path = String.format(DIVIDEND_ENDPOINT, encodedSymbol);
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(path)
                .queryParam(ASSET_CLASS_PARAM_NAME, assetClass)
                .build(true)
                .toUri();
    }
}
