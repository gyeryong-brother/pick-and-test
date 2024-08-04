package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DividendResponse(
        String symbol,
        @JsonProperty(value = "data")
        List<DividendDetail> dividendDetails
) {
}
