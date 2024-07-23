package com.gyeryongbrother.pickandtest.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import java.time.LocalDate;

public class StockEntityFixture {

    public static StockEntity stockEntity() {
        return StockEntity.builder()
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .listingDate(januaryFirst())
                .build();
    }

    public static StockEntity stockEntity(
            String name,
            String symbol,
            StockExchange stockExchange,
            LocalDate listingDate
    ) {
        return StockEntity.builder()
                .name(name)
                .symbol(symbol)
                .stockExchange(stockExchange)
                .listingDate(listingDate)
                .build();
    }
}
