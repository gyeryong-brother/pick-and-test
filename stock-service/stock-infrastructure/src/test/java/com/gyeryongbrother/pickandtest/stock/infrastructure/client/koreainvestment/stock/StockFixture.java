package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;

public class StockFixture {

    public static Stock apple() {
        return Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .outstandingShares(15334100000L)
                .listingDate(null)
                .build();
    }

    public static Stock microsoft() {
        return Stock.builder()
                .name("MICROSOFT CORP")
                .symbol("MSFT")
                .stockExchange(NASDAQ)
                .outstandingShares(7432310000L)
                .listingDate(null)
                .build();
    }

    public static Stock nvidia() {
        return Stock.builder()
                .name("NVIDIA CORP")
                .symbol("NVDA")
                .stockExchange(NASDAQ)
                .outstandingShares(24600000000L)
                .listingDate(null)
                .build();
    }
}
