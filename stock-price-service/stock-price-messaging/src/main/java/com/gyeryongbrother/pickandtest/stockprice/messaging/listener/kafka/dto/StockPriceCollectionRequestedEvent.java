package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto;

public record StockPriceCollectionRequestedEvent(
        Long stockId,
        String symbol
) {
}
