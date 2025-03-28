package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto;

public record StockCreatedEvent(
        Long id,
        String symbol,
        String stockExchange
) {
}
