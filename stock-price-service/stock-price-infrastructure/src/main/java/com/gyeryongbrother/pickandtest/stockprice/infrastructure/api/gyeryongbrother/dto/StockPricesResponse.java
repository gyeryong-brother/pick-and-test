package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto;

import java.util.List;

public record StockPricesResponse(
        List<StockPriceResponse> stockPrices
) {
}
