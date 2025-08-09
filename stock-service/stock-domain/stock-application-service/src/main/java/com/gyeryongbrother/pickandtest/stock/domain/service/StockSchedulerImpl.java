package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockScheduler;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.message.publisher.StockMessagePublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockSchedulerImpl implements StockScheduler {

    private final StockQueryRepository stockQueryRepository;
    private final StockMessagePublisher stockMessagePublisher;

    @Override
    public void collectStockPrices() {
        List<Stock> stocks = stockQueryRepository.findAll();
        stocks.forEach(stockMessagePublisher::publishStockPriceCollectionRequestedEvent);
    }

    @Override
    public void collectStockMinutePrices() {
        List<Stock> stocks = stockQueryRepository.findAll();
        stocks.forEach(stockMessagePublisher::publishStockMinutePriceCollectionRequestedEvent);
    }
}
