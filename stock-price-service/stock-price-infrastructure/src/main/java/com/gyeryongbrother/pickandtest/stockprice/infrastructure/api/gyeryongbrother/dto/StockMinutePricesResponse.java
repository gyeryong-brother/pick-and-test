package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto;

import java.util.List;

public record StockMinutePricesResponse(
        List<StockMinutePriceResponse> stockPrices
) {
}
