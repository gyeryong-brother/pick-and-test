package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;

public class Stock {

    private final Long id;
    private final String symbol;
    private final StockExchange stockExchange;

    public Stock(Long id, String symbol, StockExchange stockExchange) {
        this.id = id;
        this.symbol = symbol;
        this.stockExchange = stockExchange;
    }

    public Long id() {
        return id;
    }

    public String symbol() {
        return symbol;
    }

    public StockExchange stockExchange() {
        return stockExchange;
    }
}
