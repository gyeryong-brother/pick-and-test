package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto;

public record StockCreatedEvent(
        Long id,
        String symbol,
        String stockExchange
) {
}
