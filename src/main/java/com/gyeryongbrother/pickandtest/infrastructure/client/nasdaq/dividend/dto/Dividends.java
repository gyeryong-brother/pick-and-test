package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Dividends(
        @JsonProperty("rows")
        List<DividendDetail> dividendDetails
) {
}
