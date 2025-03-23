package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.ContinuityCode;

public record StockPriceResponse(
        ContinuityCode continuityCode,
        StockPriceBody stockPriceBody
) {
}
