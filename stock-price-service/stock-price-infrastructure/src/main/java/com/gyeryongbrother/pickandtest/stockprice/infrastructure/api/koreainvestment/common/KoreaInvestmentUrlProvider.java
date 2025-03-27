package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.Period;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KoreaInvestmentUrlProvider {

    private static final String DOMAIN = "https://openapi.koreainvestment.com:9443";
    private static final String STOCK_PRICE_ENDPOINT = "/uapi/overseas-price/v1/quotations/dailyprice";
    private static final String TOKEN_ENDPOINT = "/oauth2/tokenP";

    public String getStockPriceEndpoint(
            StockExchangeCode stockExchangeCode,
            String symbol,
            Period period,
            LocalDate date
    ) {
        return UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path(STOCK_PRICE_ENDPOINT)
                .queryParam("AUTH", "")
                .queryParam("EXCD", stockExchangeCode.getExchangeCode())
                .queryParam("SYMB", symbol)
                .queryParam("GUBN", period.code())
                .queryParam("BYMD", DateTimeHandler.toDate(date))
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
