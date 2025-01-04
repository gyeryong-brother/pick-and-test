package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class StockDetail {

    private final Stock stock;
    private final StockPrices stockPrices;
    private final Dividends dividends;

    public BigDecimal getLastStockPrice() {
        return stockPrices.getLastStockPrice();
    }

    public BigDecimal calculateCompoundAnnualGrowthRate() {
        return stockPrices.calculateCompoundAnnualGrowthRate();
    }

    public BigDecimal calculateDividendYield() {
        return dividends.calculateDividendYield(getLastStockPrice());
    }
}
