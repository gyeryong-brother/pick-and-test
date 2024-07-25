package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;

import java.util.List;

public class StockWithPricesFixture {

    public static StockWithPrices stockWithPrices(Long stockId, List<StockPrice> stockPrices) {
        return StockWithPrices.builder()
                .stock(stock(stockId))
                .stockPrices(stockPrices)
                .build();
    }
}
