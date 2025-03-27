package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.DateTimeHandler;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StockPriceDetail(
        @JsonProperty(value = "xymd")
        String date,
        @JsonProperty(value = "clos")
        String price
) {

    public StockPrice toDomain(Long stockId) {
        return new StockPrice(
                null,
                stockId,
                DateTimeHandler.toDate(date),
                new BigDecimal(price)
        );
    }
}
