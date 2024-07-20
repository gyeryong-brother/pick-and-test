package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockSymbolData(
        @JsonProperty(value = "table")
        StockSymbolTable stockSymbolTable
) {
}
