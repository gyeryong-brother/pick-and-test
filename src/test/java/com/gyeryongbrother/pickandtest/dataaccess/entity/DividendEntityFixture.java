package com.gyeryongbrother.pickandtest.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryThird;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
