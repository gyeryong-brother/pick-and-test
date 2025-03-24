package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock.dto;

public record StockResponse(
        Long id,
        String symbol,
        String stockExchange
) {
}
