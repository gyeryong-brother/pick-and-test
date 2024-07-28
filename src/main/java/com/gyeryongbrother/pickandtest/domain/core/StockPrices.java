package com.gyeryongbrother.pickandtest.domain.core;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class StockPrices {

    private final List<StockPrice> values;

    public static StockPrices from(List<StockPrice> stockPrices) {
        return new StockPrices(stockPrices);
    }

    public BigDecimal getLastStockPrice() {
        if (values.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return getLastValue().getPrice();
    }

    private StockPrice getLastValue() {
        return values.get(getLastIndex());
    }

    private int getLastIndex() {
        return values.size() - 1;
    }

    public BigDecimal calculateCompoundAnnualGrowthRate() {
        if (values.isEmpty()) {
            return BigDecimal.ZERO;
        }
        StockPrice lastStockPrice = getLastValue();
        LocalDate dateFiveYearsAgo = lastStockPrice.getDateFiveYearsAgo();
        StockPrice stockPriceFiveYearsAgo = findFirstStockPriceAfterDate(dateFiveYearsAgo);
        return lastStockPrice.calculateCompoundAnnualGrowthRate(stockPriceFiveYearsAgo);
    }

    private StockPrice findFirstStockPriceAfterDate(LocalDate date) {
        return values.stream()
                .filter(it -> it.isAfter(date))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
