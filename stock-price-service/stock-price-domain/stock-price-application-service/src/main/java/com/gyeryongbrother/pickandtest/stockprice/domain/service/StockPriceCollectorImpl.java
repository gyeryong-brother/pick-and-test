package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockPriceDate;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockPriceCollectorImpl implements StockPriceCollector {

    private final StockPriceRepository stockPriceRepository;
    private final StockPriceQueryRepository stockPriceQueryRepository;
    private final StockPriceFetcher stockPriceFetcher;

    @Override
    public void collectStockPrices(Long stockId) {
        StockPriceDate lastDate = stockPriceQueryRepository.findLastDateOfStockPricesByStockId(stockId);
        List<StockPrice> stockPrices = stockPriceFetcher.fetchStockPrices(stockId, lastDate.tomorrow().value());
        stockPrices.forEach(stockPriceRepository::save);
    }
}
