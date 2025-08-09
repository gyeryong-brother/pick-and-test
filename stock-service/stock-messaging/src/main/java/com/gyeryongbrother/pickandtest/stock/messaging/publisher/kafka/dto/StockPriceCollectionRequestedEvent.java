package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto;

public record StockPriceCollectionRequestedEvent(
        Long stockId,
        String symbol
) {
}
