package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class StockDetail {

    private final Long id;
    private final Long stockId;
    private final BigDecimal lastStockPrice;
    private final BigDecimal compoundAnnualGrowthRate;
    private final BigDecimal dividendYield;

    public Long calculateMarketCapitalization(Long outstandingShares) {
        return lastStockPrice.multiply(BigDecimal.valueOf(outstandingShares)).longValue();
    }

    public Long id() {
        return id;
    }

    public Long stockId() {
        return stockId;
    }

    public BigDecimal lastStockPrice() {
        return lastStockPrice;
    }

    public BigDecimal compoundAnnualGrowthRate() {
        return compoundAnnualGrowthRate;
    }

    public BigDecimal dividendYield() {
        return dividendYield;
    }
}
