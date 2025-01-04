package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockResponse(
        @JsonProperty(value = "rt_cd")
        String isSuccess,
        @JsonProperty(value = "msg_cd")
        String responseCode,
        @JsonProperty(value = "msg1")
        String responseMessage,
        @JsonProperty(value = "output")
        StockDetail stockDetail
) {
}
