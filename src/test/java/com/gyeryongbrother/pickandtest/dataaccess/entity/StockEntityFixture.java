package com.gyeryongbrother.pickandtest.dataaccess.entity;

import com.gyeryongbrother.pickandtest.domain.core.DividendFixture;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;

public class StockEntityFixture {

    public static StockEntity stockEntity() {
        return StockEntity.builder()
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .listingDate(januaryFirst())
                .build();
    }



    public static StockEntity stockEntity(String name, String symbol) {
        return StockEntity.builder()
                .name(name)
                .symbol(symbol)
                .stockExchange(NASDAQ)
                .listingDate(januaryFirst())
                .build();
    }
}
