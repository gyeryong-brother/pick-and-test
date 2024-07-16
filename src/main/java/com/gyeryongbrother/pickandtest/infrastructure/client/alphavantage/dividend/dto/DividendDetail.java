package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DividendDetail(
        @JsonProperty(value = "ex_dividend_date")
        String date,
        String amount
) {
}
