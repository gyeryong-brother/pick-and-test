package com.gyeryongbrother.pickandtest.dividend.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.message.listener.StockMessageListener;
import com.gyeryongbrother.pickandtest.dividend.messaging.listener.kafka.dto.StockCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockKafkaListener {

    private final StockMessageListener stockMessageListener;

    @KafkaListener(groupId = "dividend-listener", topics = "stock-created-event")
    public void receive(@Payload StockCreatedEvent event) {
        log.info("stock-created-event-received. stockId:{}, symbol:{}, stockExchange:{}", event.id(), event.symbol(), event.stockExchange());
        stockMessageListener.stockCreated(new Stock(
                event.id(),
                event.symbol()
        ));
    }
}
