package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;

public class StockFixture {

    public static Stock stock(Long id) {
        return Stock.builder()
                .id(id)
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }

    public static Stock stock(String name, String symbol) {
        return Stock.builder()
                .name(name)
                .symbol(symbol)
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }
}
