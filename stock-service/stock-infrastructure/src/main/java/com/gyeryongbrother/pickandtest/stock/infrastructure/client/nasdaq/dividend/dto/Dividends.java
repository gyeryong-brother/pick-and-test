package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Dividends(
        @JsonProperty("rows")
        List<DividendDetail> dividendDetails
) {

    List<DividendDetail> getDividendDetails() {
        if (dividendDetails == null) {
            return List.of();
        }
        return dividendDetails;
    }
}
