package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto;

public record StockMinutePriceCollectionRequestedEvent(
        Long stockId,
        String symbol
) {
}
