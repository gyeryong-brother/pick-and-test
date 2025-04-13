package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StockSymbolTable(
        @JsonProperty(value = "rows")
        List<StockSymbolDetail> stockSymbolDetails
) {
}
