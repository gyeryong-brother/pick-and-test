package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.message.publisher.StockMessagePublisher;
import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockPriceCollectionRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockKafkaProducer implements StockMessagePublisher {

    private final KafkaTemplate<String, StockPriceCollectionRequestedEvent> kafkaTemplate;

    @Override
    public void publishStockPriceCollectionRequestedEvent(Stock stock) {
        StockPriceCollectionRequestedEvent stockPriceCollectionRequestedEvent = new StockPriceCollectionRequestedEvent(
                stock.id(),
                stock.symbol()
        );
        log.info("sending stock-price-collection-requested-event. id: {}, symbol: {}", stock.id(), stock.symbol());
        kafkaTemplate.send("stock-price-collection-requested-event", stock.id().toString(), stockPriceCollectionRequestedEvent);
    }
}
