package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.dividend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record DividendResponse(
        @JsonProperty("data")
        DividendData dividendData
) {

    public List<DividendDetail> dividendDetails() {
        return dividendData.dividends().getDividendDetails();
    }
}
