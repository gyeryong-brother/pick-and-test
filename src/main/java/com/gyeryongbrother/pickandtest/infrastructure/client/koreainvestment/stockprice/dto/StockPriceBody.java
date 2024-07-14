package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StockPriceBody(
        @JsonProperty(value = "rt_cd")
        String isSuccess,
        @JsonProperty(value = "msg_cd")
        String responseCode,
        @JsonProperty(value = "msg1")
        String responseMessage,
        @JsonProperty(value = "output2")
        List<StockPriceInformation> stockPriceInformation
) {
}
