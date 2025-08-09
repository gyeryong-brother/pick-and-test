package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockMinutePrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockMinutePriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockMinutePriceRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockMinutePriceCollectorImpl implements StockMinutePriceCollector {

    private final StockMinutePriceRepository stockMinutePriceRepository;
    private final StockPriceFetcher stockPriceFetcher;

    @Override
    public void collectStockMinutePrices(Stocks stocks) {
        log.info("Start fetch stock minute prices for stocks: {}", stocks.symbols());
        List<StockMinutePrice> stockMinutePrices = stockPriceFetcher.fetchStockMinutePrices(stocks, null);
        log.info("End fetch stock minute prices for stocks: {}", stocks.symbols());
        log.info("Start save stock minute prices. size: {}", stockMinutePrices.size());
        stockMinutePriceRepository.saveAll(stockMinutePrices);
        log.info("End save stock minute prices. size: {}", stockMinutePrices.size());
    }
}
