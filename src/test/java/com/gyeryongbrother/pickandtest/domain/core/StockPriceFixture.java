package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;

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

    private static StockPrice stockPrice(Long stockId, LocalDate date, BigDecimal price) {
        return StockPrice.builder()
                .stockId(stockId)
                .date(date)
                .price(price)
                .build();
    }

    public static StockPrice stockPrice(LocalDate date, BigDecimal price) {
        return StockPrice.builder()
                .date(date)
                .price(price)
                .build();
    }
}
