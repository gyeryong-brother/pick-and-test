package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;

public record FetchStockPriceResponse(
        ContinuityCode continuityCode,
        StockPriceBody stockPriceBody
) {
}
