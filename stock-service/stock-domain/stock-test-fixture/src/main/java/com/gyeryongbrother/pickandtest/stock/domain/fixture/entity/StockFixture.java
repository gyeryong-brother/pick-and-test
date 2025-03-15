package com.gyeryongbrother.pickandtest.stock.domain.fixture.entity;

import static com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.LocalDateFixture.januaryFirst;
import static lombok.AccessLevel.PRIVATE;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
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

    public static Stock stock(Long id) {
        return Stock.builder()
                .id(id)
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }

    public static Stock stock(String name, String symbol) {
        return Stock.builder()
                .name(name)
                .symbol(symbol)
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .build();
    }

    public static Stock stock(Long id, StockDetail stockDetail) {
        return Stock.builder()
                .id(id)
                .name("Apple Inc.")
                .symbol("AAPL")
                .stockExchange(NASDAQ)
                .outstandingShares(1000L)
                .listingDate(januaryFirst())
                .stockDetail(stockDetail)
                .build();
    }
}
