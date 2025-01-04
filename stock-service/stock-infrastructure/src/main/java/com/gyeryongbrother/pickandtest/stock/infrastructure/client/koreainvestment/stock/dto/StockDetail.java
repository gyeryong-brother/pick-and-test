package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StockDetail(
        @JsonProperty(value = "prdt_eng_name")
        String productEnglishName,
        @JsonProperty(value = "lstg_stck_num")
        String listingStockNumber,
        @JsonProperty(value = "lstg_date")
        String listingDate
) {
}
