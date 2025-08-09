package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener.StockMessageListener;
import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockMinutePriceCollectionRequestedEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockMinutePriceCollectionRequestedEventKafkaListener {

    private final StockMessageListener stockMessageListener;

    @KafkaListener(
            groupId = "stock-minute-price-listener",
            topics = "stock-minute-price-collection-requested-event",
            containerFactory = "stockMinutePriceCollectionRequestedEventKafkaListenerContainerFactory"
    )
    public void receiveStockMinutePriceCollectionRequestedEvents(@Payload List<StockMinutePriceCollectionRequestedEvent> events) {
        Stocks stocks = toStocks(events);
        log.info("Start processing StockMinutePriceCollectionRequestedEvent symbols: {}", stocks.symbols());
        stockMessageListener.stockMinutePriceCollectionRequested(stocks);
        log.info("End processing StockMinutePriceCollectionRequestedEvent symbols: {}", stocks.symbols());
    }

    private Stocks toStocks(List<StockMinutePriceCollectionRequestedEvent> events) {
        List<Stock> stocks = events.stream()
                .map(this::toStock)
                .toList();
        return new Stocks(stocks);
    }

    private Stock toStock(StockMinutePriceCollectionRequestedEvent event) {
        return new Stock(
                event.stockId(),
                event.symbol()
        );
    }
}
