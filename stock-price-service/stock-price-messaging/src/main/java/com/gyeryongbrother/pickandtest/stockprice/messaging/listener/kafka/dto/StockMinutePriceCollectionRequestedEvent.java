package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto;

public record StockMinutePriceCollectionRequestedEvent(
        Long stockId,
        String symbol
) {
}
