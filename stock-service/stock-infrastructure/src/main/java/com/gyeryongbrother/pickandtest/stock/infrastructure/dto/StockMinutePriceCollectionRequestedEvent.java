package com.gyeryongbrother.pickandtest.stock.infrastructure.dto;

public record StockMinutePriceCollectionRequestedEvent(
        Long stockId,
        String symbol
) {
}
