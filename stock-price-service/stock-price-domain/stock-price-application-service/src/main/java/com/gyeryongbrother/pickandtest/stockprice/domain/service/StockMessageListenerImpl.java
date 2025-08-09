package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockMinutePriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.message.listener.StockMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockMessageListenerImpl implements StockMessageListener {

    private final StockPriceCollector stockPriceCollector;
    private final StockMinutePriceCollector stockMinutePriceCollector;

    @Override
    public void stockPriceCollectionRequested(Stocks stocks) {
        stockPriceCollector.collectStockPrices(stocks);
    }

    @Override
    public void stockMinutePriceCollectionRequested(Stocks stocks) {
        stockMinutePriceCollector.collectStockMinutePrices(stocks);
    }
}
