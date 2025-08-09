package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stocks;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StockPriceCollectorImpl implements StockPriceCollector {

    private final StockPriceRepository stockPriceRepository;
    private final StockPriceFetcher stockPriceFetcher;

    @Override
    public void collectStockPrices(Stocks stocks) {
        log.info("Start fetch stock prices for stocks: {}", stocks.symbols());
        List<StockPrice> stockPrices = stockPriceFetcher.fetchStockPrices(stocks, null);
        log.info("End fetch stock prices for stocks: {}", stocks.symbols());
        log.info("Start save stock prices. size: {}", stockPrices.size());
        stockPriceRepository.saveAll(stockPrices);
        log.info("End save stock prices. size: {}", stockPrices.size());
    }
}
