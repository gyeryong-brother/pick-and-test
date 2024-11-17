package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.stocksymbol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StockSymbolTable(
        @JsonProperty(value = "rows")
        List<StockSymbolDetail> stockSymbolDetails
) {

    List<StockSymbolDetail> getStockSymbolDetails() {
        if (stockSymbolDetails == null) {
            return List.of();
        }
        return stockSymbolDetails;
    }
}
