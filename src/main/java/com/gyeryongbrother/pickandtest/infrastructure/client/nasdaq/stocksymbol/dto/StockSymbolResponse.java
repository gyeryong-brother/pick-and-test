package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StockSymbolResponse(
        @JsonProperty(value = "data")
        StockSymbolData stockSymbolData
) {

    public List<StockSymbolDetail> stockSymbolDetails() {
        return stockSymbolData.stockSymbolTable()
                .getStockSymbolDetails();
    }
}
