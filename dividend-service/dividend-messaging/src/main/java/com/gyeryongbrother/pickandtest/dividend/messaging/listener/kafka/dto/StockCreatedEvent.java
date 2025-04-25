package com.gyeryongbrother.pickandtest.dividend.messaging.listener.kafka.dto;

public record StockCreatedEvent(
        Long id,
        String symbol,
        String stockExchange
) {
}
