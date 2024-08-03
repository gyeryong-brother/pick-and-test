package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DividendFixture {

    public static List<Dividend> appleDividends() {
        return List.of(
                dividend(2023, 8, 0.24),
                dividend(2023, 5, 0.24),
                dividend(2023, 2, 0.23)
        );
    }

    public static List<Dividend> appleDividendsAtVariousYear() {
        return List.of(
                dividend(2020, 3, 0.22),
                dividend(2020, 6, 0.23),
                dividend(2021, 4, 0.32)
        );
    }

    private static Dividend dividend(int year, int month, double amount) {
        return Dividend.builder()
                .date(LocalDate.of(year, month, 10))
                .amount(BigDecimal.valueOf(amount))
                .build();
    }

    public static List<Dividend> microsoftDividends() {
        return List.of(
                dividend(2023, 8, 0.68),
                dividend(2023, 5, 0.68),
                dividend(2023, 2, 0.68)
        );
    }

    public static List<Dividend> nvidiaDividends() {
        return List.of(
                dividend(2023, 9, 0.04),
                dividend(2023, 6, 0.04),
                dividend(2023, 3, 0.04)
        );
    }
}
