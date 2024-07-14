package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UrlProvider {

    private static final String DOMAIN = "https://openapi.koreainvestment.com:9443";
    private static final String STOCK_ENDPOINT = "/uapi/overseas-price/v1/quotations/search-info";
    private static final String STOCK_EXCHANGE_CODE_HEADER_NAME = "PRDT_TYPE_CD";
    private static final String SYMBOL_HEADER_NAME = "PDNO";
    private static final String STOCK_PRICE_ENDPOINT = "/uapi/overseas-price/v1/quotations/daily-price";
    private static final String TOKEN_ENDPOINT = "/oauth2/tokenP";

    public String getStockEndpoint(StockExchange stockExchange, String symbol) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(STOCK_ENDPOINT)
                .queryParam(STOCK_EXCHANGE_CODE_HEADER_NAME, stockExchange.getProductTypeCode())
                .queryParam(SYMBOL_HEADER_NAME, symbol)
                .build()
                .toUriString();
    }

    public String getStockPriceEndpoint(
            StockExchange stockExchange,
            String symbol,
            Period period,
            String date
    ) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(STOCK_PRICE_ENDPOINT)
                .queryParam("AUTH", "")
                .queryParam("EXCD", stockExchange.getExchangeCode())
                .queryParam("SYMB", symbol)
                .queryParam("GUBN", period.getCode())
                .queryParam("BYMD", date)
                .queryParam("MODP", "1")
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
