package com.gyeryongbrother.pickandtest.dividend.domain.fixture.entity;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DividendFixture {

    private DividendFixture() {
    }

    public static Dividend dividend(
            Long stockId,
            int year,
            int month,
            double amount
    ) {
        return new Dividend(
                null,
                stockId,
                LocalDate.of(year, month, 10),
                BigDecimal.valueOf(amount)
        );
    }

    public static Dividend dividend(
            int year,
            int month,
            double amount) {
        return dividend(null, year, month, amount);
    }
}
