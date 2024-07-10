package com.gyeryongbrother.pickandtest.dataaccess.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DividendEntityFixture {

    public static DividendEntity dividendEntity(
            StockEntity stockEntity,
            LocalDate date,
            BigDecimal amount
    ) {
        return DividendEntity.builder()
                .stock(stockEntity)
                .date(date)
                .amount(amount)
                .build();
    }
}
