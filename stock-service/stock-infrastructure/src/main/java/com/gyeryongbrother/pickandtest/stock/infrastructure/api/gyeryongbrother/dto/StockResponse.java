package com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockResponse(
        String name,
        String symbol,
        @JsonProperty("stock_exchange")
        String stockExchange,
        @JsonProperty("outstanding_shares")
        String outstandingShares
) {
}
