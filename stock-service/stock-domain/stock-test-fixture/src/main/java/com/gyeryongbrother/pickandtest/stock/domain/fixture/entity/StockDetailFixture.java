package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class StockDetailFixture {

    public static StockDetail firstStockDetail(Long stockId) {
        return StockDetail.builder()
                .stockId(stockId)
                .lastStockPrice(BigDecimal.valueOf(1000))
                .compoundAnnualGrowthRate(BigDecimal.valueOf(58.49))
                .dividendYield(BigDecimal.valueOf(0.2))
                .build();
    }

    public static StockDetail secondStockDetail(Long stockId) {
        return StockDetail.builder()
                .stockId(stockId)
                .lastStockPrice(BigDecimal.valueOf(100))
                .compoundAnnualGrowthRate(BigDecimal.valueOf(0))
                .dividendYield(BigDecimal.valueOf(0.5))
                .build();
    }
}
