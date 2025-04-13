package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DividendDetail(
        @JsonProperty("exOrEffDate")
        String date,
        String amount
) {
}
