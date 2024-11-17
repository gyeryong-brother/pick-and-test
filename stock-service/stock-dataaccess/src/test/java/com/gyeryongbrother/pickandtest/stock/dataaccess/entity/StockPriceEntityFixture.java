package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockPriceEntityFixture {

    public static List<StockPriceEntity> stockPriceEntities(StockEntity stockEntity) {
        return List.of(
                stockPriceEntity(stockEntity, januaryFirst(), oneHundred()),
                stockPriceEntity(stockEntity, januarySecond(), twoHundred())
        );
    }

    private static StockPriceEntity stockPriceEntity(
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
