package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener.StockMessageListener;
import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockCreatedEvent;
import java.util.List;
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

//    @KafkaListener(groupId = "stock-price-listener", topics = "stock-created-event")
//    public void receive(@Payload List<StockCreatedEvent> events) {
//        List<Stock> stocks = events.stream()
//                .map(this::toStock)
//                .toList();
//        stockMessageListener.stockCreated(new Stocks(stocks));
//    }
//
//    private Stock toStock(StockCreatedEvent event) {
//        return new Stock(
//                event.id(),
//                event.symbol(),
//                StockExchange.valueOf(event.stockExchange())
//        );
//    }

    @KafkaListener(groupId = "stock-price-listener", topics = "stock-created-event")
    public void receive(@Payload StockCreatedEvent event) {
        stockMessageListener.stockCreated(new Stocks(List.of(toStock(event))));
    }

    private Stock toStock(StockCreatedEvent event) {
        return new Stock(
                event.id(),
                event.symbol(),
                StockExchange.valueOf(event.stockExchange())
        );
    }
}
