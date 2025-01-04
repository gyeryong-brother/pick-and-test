package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DividendFixture {

    public static List<Dividend> appleDividends() {
        return List.of(
                dividend(8, 0.24),
                dividend(5, 0.24),
                dividend(2, 0.23)
        );
    }

    private static Dividend dividend(int month, double amount) {
        return Dividend.builder()
                .date(LocalDate.of(2023, month, 10))
                .amount(BigDecimal.valueOf(amount))
                .build();
    }

    public static List<Dividend> microsoftDividends() {
        return List.of(
                dividend(8, 0.68),
                dividend(5, 0.68),
                dividend(2, 0.68)
        );
    }

    public static List<Dividend> nvidiaDividends() {
        return List.of(
                dividend(9, 0.04),
                dividend(6, 0.04),
                dividend(3, 0.04)
        );
    }
}
