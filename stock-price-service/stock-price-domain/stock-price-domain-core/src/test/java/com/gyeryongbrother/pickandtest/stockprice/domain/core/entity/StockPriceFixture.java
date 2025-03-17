package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.LocalDateFixture.januarySecond;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockPriceFixture {

    public static List<StockPrice> stockPrices(Long stockId) {
        return List.of(
                stockPrice(stockId, januaryFirst(), oneHundred()),
                stockPrice(stockId, januarySecond(), twoHundred())
        );
    }

    public static StockPrice stockPrice(Long stockId, LocalDate date, BigDecimal price) {
        return new StockPrice(
                null,
                stockId,
                date,
                price
        );
    }

    public static StockPrice stockPrice(LocalDate date, BigDecimal price) {
        return new StockPrice(null, null, date, price);
    }
}
