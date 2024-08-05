package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import com.gyeryongbrother.pickandtest.domain.core.Stock;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;

public class StockFixture {

    public static Stock apple() {
        return Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .build();
    }

    public static Stock appleWithDividendsOfDifferentYears() {
        return Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .build();
    }

    public static Stock microsoft() {
        return Stock.builder()
                .name("MICROSOFT CORP")
                .symbol("MSFT")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .build();
    }

    public static Stock nvidia() {
        return Stock.builder()
                .name("NVIDIA CORP")
                .symbol("NVDA")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .build();
    }
}
