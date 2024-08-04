package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import com.gyeryongbrother.pickandtest.domain.core.StockDetail;

import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFixture.*;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.*;

public class StockDetailFixture {

    public static StockDetail apple() {
        return StockDetail.builder()
                .stock(StockFixture.apple())
                .stockPrices(appleStockPrices())
                .dividends(appleDividends())
                .build();
    }

    public static StockDetail appleWithDividendsOfVariousYear() {
        return StockDetail.builder()
                .stock(StockFixture.apple())
                .stockPrices(appleStockPrices())
                .dividends(appleDividendsAtVariousYear())
                .build();
    }

    public static StockDetail microsoft() {
        return StockDetail.builder()
                .stock(StockFixture.microsoft())
                .stockPrices(microsoftStockPrices())
                .dividends(microsoftDividends())
                .build();
    }

    public static StockDetail nvidia() {
        return StockDetail.builder()
                .stock(StockFixture.nvidia())
                .stockPrices(nvidiaStockPrices())
                .dividends(nvidiaDividends())
                .build();
    }
}
