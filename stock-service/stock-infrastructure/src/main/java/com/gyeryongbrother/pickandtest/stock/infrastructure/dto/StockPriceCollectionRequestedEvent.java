package com.gyeryongbrother.pickandtest.stock.infrastructure.dto;

public record StockPriceCollectionRequestedEvent(
        Long stockId,
        String symbol
) {
}
