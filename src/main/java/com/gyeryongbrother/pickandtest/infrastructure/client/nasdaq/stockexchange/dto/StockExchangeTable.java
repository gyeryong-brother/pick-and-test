package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StockExchangeTable(
        @JsonProperty(value = "rows")
        List<StockExchangeDetail> stockExchangeDetails
) {
}
