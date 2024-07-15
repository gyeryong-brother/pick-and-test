package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StockPriceDetail(
        @JsonProperty(value = "xymd")
        String date,
        @JsonProperty(value = "clos")
        String price
) {
}
