package com.gyeryongbrother.pickandtest.dividend.domain.core.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Dividend {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal amount;

    public Dividend(
            Long id,
            Long stockId,
            LocalDate date,
            BigDecimal amount) {
        this.id = id;
        this.stockId = stockId;
        this.date = date;
        this.amount = amount;
    }

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

    public int year() {
        return date.getYear();
    }

    public Long id() {
        return id;
    }

    public Long stockId() {
        return stockId;
    }

    public LocalDate date() {
        return date;
    }

    public BigDecimal amount() {
        return amount;
    }
}
