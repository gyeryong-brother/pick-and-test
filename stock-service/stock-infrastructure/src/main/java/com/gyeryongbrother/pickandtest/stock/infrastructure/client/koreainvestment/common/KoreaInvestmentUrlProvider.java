package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KoreaInvestmentUrlProvider {

    private static final String DOMAIN = "https://openapi.koreainvestment.com:9443";
    private static final String STOCK_ENDPOINT = "/uapi/overseas-price/v1/quotations/search-info";
    private static final String STOCK_EXCHANGE_CODE_HEADER_NAME = "PRDT_TYPE_CD";
    private static final String SYMBOL_HEADER_NAME = "PDNO";
    private static final String TOKEN_ENDPOINT = "/oauth2/tokenP";

    public String getStockEndpoint(StockExchangeCode stockExchangeCode, String symbol) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(STOCK_ENDPOINT)
                .queryParam(STOCK_EXCHANGE_CODE_HEADER_NAME, stockExchangeCode.getProductTypeCode())
                .queryParam(SYMBOL_HEADER_NAME, symbol)
                .build()
                .toUriString();
    }

    public String getTokenEndpoint() {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(TOKEN_ENDPOINT)
                .build()
                .toUriString();
    }
}
