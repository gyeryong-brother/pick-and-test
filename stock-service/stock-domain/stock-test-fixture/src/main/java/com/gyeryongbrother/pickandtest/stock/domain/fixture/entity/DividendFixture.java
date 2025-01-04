package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryThird;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DividendFixture {

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

    public static List<Dividend> dividends() {
        return List.of(
                dividend(LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
                dividend(LocalDate.of(2023, 6, 1), BigDecimal.valueOf(0.2)),
                dividend(LocalDate.of(2023, 9, 1), BigDecimal.valueOf(0.3)),
                dividend(LocalDate.of(2023, 12, 1), BigDecimal.valueOf(0.4)),
                dividend(LocalDate.of(2024, 3, 1), BigDecimal.valueOf(0.5))
        );
    }

    public static List<Dividend> twoDividends() {
        return List.of(
                dividend(LocalDate.of(2023, 3, 1), BigDecimal.valueOf(0.1)),
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
