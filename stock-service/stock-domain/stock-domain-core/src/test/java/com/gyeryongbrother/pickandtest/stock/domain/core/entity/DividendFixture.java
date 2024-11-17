package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.LocalDateFixture.januaryThird;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DividendFixture {

    public static List<Dividend> dividends() {
        return List.of(
                dividend(LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
                dividend(LocalDate.of(2023, 6, 1), BigDecimal.valueOf(0.2)),
                dividend(LocalDate.of(2023, 9, 1), BigDecimal.valueOf(0.3)),
                dividend(LocalDate.of(2023, 12, 1), BigDecimal.valueOf(0.4)),
                dividend(LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        );
    }

    public static List<Dividend> dividends(Long stockId) {
        return List.of(
                dividend(stockId, januaryFirst(), oneHundred()),
                dividend(stockId, januarySecond(), twoHundred()),
                dividend(stockId, januaryThird(), threeHundred())
        );
    }

    private static Dividend dividend(Long stockId, LocalDate date, BigDecimal amount) {
        return Dividend.builder()
                .stockId(stockId)
                .date(date)
                .amount(amount)
                .build();
    }

    public static Dividend dividend(LocalDate date, BigDecimal amount) {
        return Dividend.builder()
                .date(date)
                .amount(amount)
                .build();
    }

    public static Dividend dividend(LocalDate date) {
        return Dividend.builder()
                .date(date)
                .build();
    }

    public static Dividend dividend(BigDecimal amount) {
        return Dividend.builder()
                .amount(amount)
                .build();
    }
}
