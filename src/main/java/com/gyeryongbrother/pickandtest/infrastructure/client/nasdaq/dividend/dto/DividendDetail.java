package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DividendDetail(
        @JsonProperty("exOrEffDate")
        String date,
        String amount
) {
}
