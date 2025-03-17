package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.exception.StockPriceCoreExceptionType.INVALID_YEAR;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.exception.StockPriceCoreException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class StockPrice {

    private final Long id;
    private final Long stockId;
    private final LocalDate date;
    private final BigDecimal price;

    public StockPrice(
            Long id,
            Long stockId,
            LocalDate date,
            BigDecimal price
    ) {
        this.id = id;
        this.stockId = stockId;
        this.date = date;
        this.price = price;
    }

    public LocalDate getDateFiveYearsAgo() {
        return date.minusYears(5);
    }

    public boolean isAfter(LocalDate other) {
        return date.isAfter(other);
    }

    public BigDecimal calculateCompoundAnnualGrowthRate(StockPrice other) {
        if (hasSameYear(other)) {
            return BigDecimal.ZERO;
        }
        if (date.isAfter(other.date)) {
            return other.calculateCompoundAnnualGrowthRate(this);
        }
        int yearsDifference = calculateYearsDifference(other.date());
        double compoundAnnualGrowthRate = calculateCompoundAnnualGrowthRate(other.price, yearsDifference);
        return BigDecimal.valueOf(compoundAnnualGrowthRate * 100).setScale(2, RoundingMode.HALF_EVEN);
    }

    private boolean hasSameYear(StockPrice other) {
        int thisYear = date.getYear();
        int otherYear = other.date.getYear();
        return thisYear == otherYear;
    }

    private int calculateYearsDifference(LocalDate other) {
        if (date.getYear() < other.getYear()) {
            return other.getYear() - date.getYear();
        }
        throw new StockPriceCoreException(INVALID_YEAR);
    }

    private double calculateCompoundAnnualGrowthRate(BigDecimal other, int year) {
        double thisPrice = price.doubleValue();
        double otherPrice = other.doubleValue();
        return Math.pow(otherPrice / thisPrice, 1.0 / year) - 1;
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

    public BigDecimal price() {
        return price;
    }
}
