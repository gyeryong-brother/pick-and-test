package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.appleStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.microsoftStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.nvidiaStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.DividendFixture.appleDividends;
import static com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.DividendFixture.appleDividendsAtVariousYear;
import static com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.DividendFixture.microsoftDividends;
import static com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.DividendFixture.nvidiaDividends;

import com.gyeryongbrother.pickandtest.domain.core.Dividends;
import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.core.StockPrices;

public class StockDetailFixture {

    public static StockDetail apple() {
        return StockDetail.builder()
                .stock(StockFixture.apple())
                .stockPrices(StockPrices.from(appleStockPrices()))
                .dividends(Dividends.from(appleDividends()))
                .build();
    }

    public static StockDetail appleWithDividendsOfVariousYear() {
        return StockDetail.builder()
                .stock(StockFixture.apple())
                .stockPrices(StockPrices.from(appleStockPrices()))
                .dividends(Dividends.from(appleDividendsAtVariousYear()))
                .build();
    }

    public static StockDetail microsoft() {
        return StockDetail.builder()
                .stock(StockFixture.microsoft())
                .stockPrices(StockPrices.from(microsoftStockPrices()))
                .dividends(Dividends.from(microsoftDividends()))
                .build();
    }

    public static StockDetail nvidia() {
        return StockDetail.builder()
                .stock(StockFixture.nvidia())
                .stockPrices(StockPrices.from(nvidiaStockPrices()))
                .dividends(Dividends.from(nvidiaDividends()))
                .build();
    }
}
