package com.gyeryongbrother.pickandtest.dataaccess.entity;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import java.time.LocalDate;

public class StockEntityFixture {

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
