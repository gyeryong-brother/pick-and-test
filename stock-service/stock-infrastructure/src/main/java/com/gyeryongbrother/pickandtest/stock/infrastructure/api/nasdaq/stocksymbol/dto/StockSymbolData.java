package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockSymbolData(
        @JsonProperty(value = "table")
        StockSymbolTable stockSymbolTable
) {
}
