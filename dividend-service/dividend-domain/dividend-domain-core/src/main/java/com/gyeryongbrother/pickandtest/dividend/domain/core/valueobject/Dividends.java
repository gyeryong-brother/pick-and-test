package com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Dividends {

    private final List<Dividend> values;

    public static Dividends from(List<Dividend> values) {
        return new Dividends(values);
    }

    private Dividends(List<Dividend> values) {
        this.values = values;
    }

    public List<AnnualDividend> getAnnualDividends() {
        Map<Integer, AnnualDividend> annualDividendsByYear = values.stream()
                .collect(toMap(Dividend::year, AnnualDividend::from, AnnualDividend::add));
        return annualDividendsByYear.values().stream()
                .sorted(comparing(AnnualDividend::year))
                .toList();
    }

    public BigDecimal calculateDividendYield(BigDecimal stockPrice) {
        if (BigDecimal.ZERO.equals(stockPrice)) {
            return BigDecimal.ZERO;
        }
        if (values.isEmpty()) {
            return BigDecimal.ZERO;
        }
        Dividend lastDividend = getLastDividend();
        int yearOneYearAgo = lastDividend.getYearOneYearAgo();
        BigDecimal count = countSameYear(yearOneYearAgo);
        return lastDividend.calculateDividendYield(count, stockPrice);
    }

    private Dividend getLastDividend() {
        return values.get(getLastIndex());
    }

    private int getLastIndex() {
        return values.size() - 1;
    }

    private BigDecimal countSameYear(int year) {
        long count = values.stream()
                .filter(it -> it.hasSameYear(year))
                .count();
        return BigDecimal.valueOf(count);
    }

    public List<Dividend> values() {
        return values;
    }
}
