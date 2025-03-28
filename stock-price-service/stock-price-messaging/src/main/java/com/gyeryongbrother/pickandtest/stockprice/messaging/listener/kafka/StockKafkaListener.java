package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener.StockMessageListener;
import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockKafkaListener {

    private final StockMessageListener stockMessageListener;

    @KafkaListener(groupId = "stock-price-listener", topics = "stock-created-event")
    public void receive(@Payload StockCreatedEvent event) {
        stockMessageListener.stockCreated(new Stock(
                event.id(),
                event.symbol(),
                StockExchange.valueOf(event.stockExchange())
        ));
    }
}
