package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto;

public record StockResponse(
        Long id,
        String symbol,
        String stockExchange
) {
}
