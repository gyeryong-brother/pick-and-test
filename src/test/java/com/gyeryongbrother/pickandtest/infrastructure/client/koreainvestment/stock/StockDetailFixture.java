package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFixture.*;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.appleStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.microsoftStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.nvidiaStockPrices;

import com.gyeryongbrother.pickandtest.domain.core.DividendFixture;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;

public class StockDetailFixture {

    public static StockDetail apple() {
        return StockDetail.builder()
                .stock(StockFixture.apple())
                .stockPrices(appleStockPrices())
                .dividends(appleDividends())
                .build();
    }

    public static StockDetail appleWithDividendsOfVariousYear(){
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
