package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.AnnualDividend;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class Dividends {

    private final List<Dividend> values;

    public static Dividends from(List<Dividend> dividends) {
        return new Dividends(dividends);
    }

    public List<AnnualDividend> getAnnualDividends() {
        Map<Integer, AnnualDividend> annualDividendsByYear = values.stream()
                .collect(toMap(Dividend::getYear, AnnualDividend::from, AnnualDividend::add));
        return annualDividendsByYear.values().stream()
                .sorted(comparing(AnnualDividend::getYear))
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
}
