package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.appleDividendsAtVariousYear;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.DividendFixture.dividends;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockFixture.stock;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.appleStockPrices;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.StockPriceFixture.stockPrices;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividends;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockPrices;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class StockDetailFixture {

    public static StockDetail appleWithDividendsOfVariousYear() {
        return StockDetail.builder()
                .stock(StockFixture.apple())
                .stockPrices(StockPrices.from(appleStockPrices()))
                .dividends(Dividends.from(appleDividendsAtVariousYear()))
                .build();
    }

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
