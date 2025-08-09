package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NGM;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;

public class StockEntityFixture {

    public static StockEntity stockEntity() {
        return StockEntity.builder()
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NGM)
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
                .stockExchange(NGM)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }
}
