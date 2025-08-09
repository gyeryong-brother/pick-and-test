package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.message.publisher.StockMessagePublisher;
import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockMinutePriceCollectionRequestedEvent;
import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockPriceCollectionRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockKafkaProducer implements StockMessagePublisher {

    private static final String STOCK_PRICE_TOPIC_NAME = "stock-price-collection-requested-event";
    private static final String STOCK_MINUTE_PRICE_TOPIC_NAME = "stock-minute-price-collection-requested-event";

    private final KafkaTemplate<String, StockPriceCollectionRequestedEvent> stockPriceKafkaTemplate;
    private final KafkaTemplate<String, StockMinutePriceCollectionRequestedEvent> stockMinutePriceKafkaTemplate;

    @Override
    public void publishStockPriceCollectionRequestedEvent(Stock stock) {
        StockPriceCollectionRequestedEvent stockPriceCollectionRequestedEvent = new StockPriceCollectionRequestedEvent(
                stock.id(),
                stock.symbol()
        );
        log.info("sending stock-price-collection-requested-event. id: {}, symbol: {}", stock.id(), stock.symbol());
        stockPriceKafkaTemplate.send(
                STOCK_PRICE_TOPIC_NAME,
                stock.id().toString(),
                stockPriceCollectionRequestedEvent
        );
    }

    @Override
    public void publishStockMinutePriceCollectionRequestedEvent(Stock stock) {
        StockMinutePriceCollectionRequestedEvent stockMinutePriceCollectionRequestedEvent = new StockMinutePriceCollectionRequestedEvent(
                stock.id(),
                stock.symbol()
        );
        log.info("sending stock-minute-price-collection-requested-event. id: {}, symbol: {}", stock.id(), stock.symbol());
        stockMinutePriceKafkaTemplate.send(
                STOCK_MINUTE_PRICE_TOPIC_NAME,
                stock.id().toString(),
                stockMinutePriceCollectionRequestedEvent
        );
    }
}
