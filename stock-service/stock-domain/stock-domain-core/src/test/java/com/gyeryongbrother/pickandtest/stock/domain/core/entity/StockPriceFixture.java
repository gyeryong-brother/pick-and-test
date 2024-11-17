package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.oneThousand;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.januarySecond;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockPriceFixture {

    public static List<StockPrice> stockPrices() {
        return List.of(
                stockPrice(LocalDate.of(2018, 12, 31), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 1), twoHundred()),
                stockPrice(LocalDate.of(2019, 1, 2), oneHundred()),
                stockPrice(LocalDate.of(2019, 1, 3), threeHundred()),
                stockPrice(LocalDate.of(2024, 1, 1), oneThousand())
        );
    }

    public static StockPrice stockPrice() {
        return stockPrice(januaryFirst(), oneHundred());
    }

    public static List<StockPrice> stockPrices(Long stockId) {
        return List.of(
                stockPrice(stockId, januaryFirst(), oneHundred()),
                stockPrice(stockId, januarySecond(), twoHundred())
        );
    }

    public static StockPrice stockPrice(Long stockId, LocalDate date, BigDecimal price) {
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
