package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockExchangeResponse(
        @JsonProperty(value = "data")
        StockExchangeData stockExchangeData
) {
}
