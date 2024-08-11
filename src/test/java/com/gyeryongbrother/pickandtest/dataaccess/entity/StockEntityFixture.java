package com.gyeryongbrother.pickandtest.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;

public class StockEntityFixture {

    public static StockEntity stockEntity() {
        return StockEntity.builder()
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }

    public static StockEntity nvidiaStockEntity() {
        return stockEntity("Nvidia", "NVDA");
    }


    public static StockEntity stockEntity(String name, String symbol) {
        return StockEntity.builder()
                .name(name)
                .symbol(symbol)
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }
}
