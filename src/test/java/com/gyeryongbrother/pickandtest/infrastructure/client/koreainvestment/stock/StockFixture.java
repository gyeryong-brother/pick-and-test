package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFixture.appleDividends;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFixture.microsoftDividends;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFixture.nvidiaDividends;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.appleStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.microsoftStockPrices;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFixture.nvidiaStockPrices;

import com.gyeryongbrother.pickandtest.domain.core.Stock;

public class StockFixture {

    public static Stock apple() {
        return Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .stockPrices(appleStockPrices())
                .dividends(appleDividends())
                .build();
    }

    public static Stock microsoft() {
        return Stock.builder()
                .name("MICROSOFT CORP")
                .symbol("MSFT")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .stockPrices(microsoftStockPrices())
                .dividends(microsoftDividends())
                .build();
    }

    public static Stock nvidia() {
        return Stock.builder()
                .name("NVIDIA CORP")
                .symbol("NVDA")
                .stockExchange(NASDAQ)
                .listingDate(null)
                .stockPrices(nvidiaStockPrices())
                .dividends(nvidiaDividends())
                .build();
    }
}
