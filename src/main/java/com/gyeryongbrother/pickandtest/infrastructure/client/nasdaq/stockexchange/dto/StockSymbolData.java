package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockSymbolData(
        @JsonProperty(value = "table")
        StockSymbolTable stockSymbolTable
) {
}
