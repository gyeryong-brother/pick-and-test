package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.domain.core.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryThird;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DividendFixture {

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
