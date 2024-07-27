package com.gyeryongbrother.pickandtest.domain.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Dividend {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal amount;

    public int getYearOneYearAgo() {
        return date.getYear() - 1;
    }

    public boolean hasSameYear(int year) {
        return date.getYear() == year;
    }

    public BigDecimal calculateDividendYield(BigDecimal count, BigDecimal stockPrice) {
        return amount.multiply(count)
                .divide(stockPrice, 4, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(100));
    }
}
