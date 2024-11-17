package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.stocksymbol.dto;

import java.util.List;

public class StockSymbolResponseFixture {

    public static StockSymbolResponse stockSymbolResponse() {
        return new StockSymbolResponse(new StockSymbolData(new StockSymbolTable(List.of(
                new StockSymbolDetail("AAPL"),
                new StockSymbolDetail("MSFT"),
                new StockSymbolDetail("NVDA")
        ))));
    }
}
