package com.gyeryongbrother.pickandtest.dataaccess.entity;

import java.time.LocalDate;

public class StockEntityFixture {

    public static StockEntity stockEntity(
            String name,
            String symbol,
            LocalDate listingDate
    ) {
        return StockEntity.builder()
                .name(name)
                .symbol(symbol)
                .listingDate(listingDate)
                .build();
    }
}
