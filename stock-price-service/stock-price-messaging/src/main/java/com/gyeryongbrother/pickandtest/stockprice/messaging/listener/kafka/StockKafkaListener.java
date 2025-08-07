package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener.StockMessageListener;
import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockPriceCollectionRequestedEvent;
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

    @KafkaListener(groupId = "stock-price-listener", topics = "stock-price-collection-requested-event", containerFactory = "kafkaListenerContainerFactory")
    public void receive(@Payload List<StockPriceCollectionRequestedEvent> events) {
        Stocks stocks = toStocks(events);
        log.info("Start processing StockPriceCollectionRequestedEvent symbols: {}", stocks.symbols());
        stockMessageListener.stockPriceCollectionRequested(stocks);
        log.info("End processing StockPriceCollectionRequestedEvent symbols: {}", stocks.symbols());
    }

    private Stocks toStocks(List<StockPriceCollectionRequestedEvent> events) {
        List<Stock> stocks = events.stream()
                .map(this::toStock)
                .toList();
        return new Stocks(stocks);
    }

    private Stock toStock(StockPriceCollectionRequestedEvent event) {
        return new Stock(
                event.stockId(),
                event.symbol()
        );
    }
}
