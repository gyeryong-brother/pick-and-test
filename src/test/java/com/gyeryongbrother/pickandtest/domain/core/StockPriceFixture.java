package com.gyeryongbrother.pickandtest.domain.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januarySecond;

public class StockPriceFixture {

    public static List<StockPrice> stockPrices(Long stockId) {
        return List.of(
                stockPrice(stockId, januaryFirst(), oneHundred()),
                stockPrice(stockId, januarySecond(), twoHundred())
        );
    }

    private static StockPrice stockPrice(Long stockId, LocalDate date, BigDecimal price) {
        return StockPrice.builder()
                .stockId(stockId)
                .date(date)
                .price(price)
                .build();
    }
}
