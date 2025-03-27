package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.ContinuityCode;

public record StockPriceResponse(
        ContinuityCode continuityCode,
        StockPriceBody stockPriceBody
) {
}
