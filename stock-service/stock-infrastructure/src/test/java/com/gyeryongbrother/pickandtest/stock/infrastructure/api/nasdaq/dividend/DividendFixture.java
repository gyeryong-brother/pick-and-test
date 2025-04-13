package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend;

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
}
