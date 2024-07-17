package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockExchangeData(
        @JsonProperty(value = "table")
        StockExchangeTable stockExchangeTable
) {
}
