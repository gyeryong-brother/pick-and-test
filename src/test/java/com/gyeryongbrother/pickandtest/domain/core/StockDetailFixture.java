package com.gyeryongbrother.pickandtest.domain.core;

import java.util.List;

import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;

public class StockDetailFixture {

    public static StockDetail stockDetail(
            Long stockId,
            List<StockPrice> stockPrices,
            List<Dividend> dividends
    ) {
        return StockDetail.builder()
                .stock(stock(stockId))
                .stockPrices(stockPrices)
                .dividends(dividends)
                .build();
    }
}
