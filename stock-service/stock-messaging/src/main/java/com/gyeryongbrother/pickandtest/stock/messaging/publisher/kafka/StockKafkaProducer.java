package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.message.publisher.StockMessagePublisher;
import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockKafkaProducer implements StockMessagePublisher {

    private final KafkaTemplate<String, StockCreatedEvent> kafkaTemplate;

    @Override
    public void publishStockCreatedEvent(Stock stock) {
        StockCreatedEvent stockCreatedEvent = new StockCreatedEvent(
                stock.id(),
                stock.symbol(),
                stock.stockExchange().name()
        );
        log.info("sending stock-created-event. id: {}, symbol: {}", stock.id(), stock.symbol());
        kafkaTemplate.send("stock-created-event", stockCreatedEvent);
    }
}
