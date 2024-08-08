package com.gyeryongbrother.pickandtest.domain.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryThird;

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
}
