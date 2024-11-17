package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPriceFixture.stockPrices;

import java.util.List;

public class StockDetailFixture {

    public static StockDetail stockDetail(
            Long stockId,
            List<StockPrice> stockPrices,
            List<Dividend> dividends
    ) {
        return StockDetail.builder()
                .stock(stock(stockId))
                .stockPrices(StockPrices.from(stockPrices))
                .dividends(Dividends.from(dividends))
                .build();
    }

    public static StockDetail stockDetail(Long stockId) {
        return StockDetail.builder()
                .stock(stock(stockId))
                .stockPrices(StockPrices.from(stockPrices(stockId)))
                .dividends(Dividends.from(dividends(stockId)))
                .build();
    }
}
