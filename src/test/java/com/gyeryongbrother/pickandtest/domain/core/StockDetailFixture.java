package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.domain.core.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.domain.core.StockPriceFixture.stockPrices;

import java.util.List;

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

    public static StockDetail stockDetail(Long stockId) {
        return StockDetail.builder()
                .stock(stock(stockId))
                .stockPrices(stockPrices(stockId))
                .dividends(dividends(stockId))
                .build();
    }
}
