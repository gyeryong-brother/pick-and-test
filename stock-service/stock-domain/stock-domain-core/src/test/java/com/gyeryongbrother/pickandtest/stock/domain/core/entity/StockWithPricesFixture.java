package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockFixture.stock;

import java.util.List;

public class StockWithPricesFixture {

    public static StockWithPrices stockWithPrices(Long stockId, List<StockPrice> stockPrices) {
        return StockWithPrices.builder()
                .stock(stock(stockId))
                .stockPrices(stockPrices)
                .build();
    }
}
