package com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity;

import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stockprice.domain.fixture.valueobject.LocalDateFixture.januarySecond;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockPriceEntityFixture {

    public static List<StockPriceEntity> stockPriceEntities(Long stockId) {
        return List.of(
                stockPriceEntity(stockId, januaryFirst(), oneHundred()),
                stockPriceEntity(stockId, januarySecond(), twoHundred())
        );
    }

    private static StockPriceEntity stockPriceEntity(
            Long stockId,
            LocalDate date,
            BigDecimal price
    ) {
        return new StockPriceEntity(null, stockId, date, price);
    }
}
