package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener.StockMessageListener;
import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockKafkaListener {

    private final StockMessageListener stockMessageListener;

    @KafkaListener(groupId = "stock-price-listener", topics = "stock-created-event")
    public void receive(@Payload StockCreatedEvent event) {
        log.info("stock-created-event-received. stockId:{}, symbol:{}, stockExchange: {}", event.id(), event.symbol(), event.stockExchange());
        stockMessageListener.stockCreated(new Stock(
                event.id(),
                event.symbol(),
                StockExchange.valueOf(event.stockExchange())
        ));
    }

    @KafkaListener(groupId = "stock-minute-price-listener", topics = "stock-created-event")
    public void collectMinutePrices(@Payload StockCreatedEvent event) {
        log.info("start collecting minute prices. stockId:{}, symbol:{}, stockExchange: {}", event.id(), event.symbol(), event.stockExchange());
        stockMessageListener.collectStockMinutePrices(new Stock(
                event.id(),
                event.symbol(),
                StockExchange.valueOf(event.stockExchange())
        ));
    }
}
