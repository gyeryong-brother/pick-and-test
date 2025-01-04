package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryThird;

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
