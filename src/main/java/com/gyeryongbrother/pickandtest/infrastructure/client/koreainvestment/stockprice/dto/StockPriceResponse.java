package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;

public record StockPriceResponse(
        ContinuityCode continuityCode,
        StockPriceBody stockPriceBody
) {
}
