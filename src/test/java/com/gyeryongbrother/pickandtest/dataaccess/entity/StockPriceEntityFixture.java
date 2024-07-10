package com.gyeryongbrother.pickandtest.dataaccess.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockPriceEntityFixture {

    public static StockPriceEntity stockPriceEntity(
            StockEntity stockEntity,
            LocalDate date,
            BigDecimal price
    ) {
        return StockPriceEntity.builder()
                .stock(stockEntity)
                .date(date)
                .price(price)
                .build();
    }
}
