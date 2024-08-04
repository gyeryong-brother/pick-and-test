package com.gyeryongbrother.pickandtest.dataaccess.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.*;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.*;

public class DividendEntityFixture {

    public static List<DividendEntity> dividendEntities(StockEntity stockEntity) {
        return List.of(
                dividendEntity(stockEntity, januaryFirst(), oneHundred()),
                dividendEntity(stockEntity, januarySecond(), twoHundred()),
                dividendEntity(stockEntity, januaryThird(), threeHundred())
        );
    }

    private static DividendEntity dividendEntity(
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
