package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DividendFixture {

    public static List<Dividend> appleDividendsAtVariousYear(Long stockId) {
        return List.of(
                dividend(stockId, 2020, 3, 0.22),
                dividend(stockId, 2020, 6, 0.23),
                dividend(stockId, 2021, 4, 0.32)
        );
    }

    private static Dividend dividend(Long stockId, int year, int month, double amount) {
        return Dividend.builder()
                .stockId(stockId)
                .date(LocalDate.of(year, month, 10))
                .amount(BigDecimal.valueOf(amount))
                .build();
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
