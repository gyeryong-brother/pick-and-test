package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceRepository;
import java.time.LocalDate;
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
        LocalDate lastDate = stockPriceQueryRepository.findLastDateOfStockPricesByStockId(stockId);
        List<StockPrice> stockPrices = fetchStockPrices(stockId, lastDate);
        stockPrices.forEach(stockPriceRepository::save);
    }

    private List<StockPrice> fetchStockPrices(Long stockId, LocalDate lastDate) {
        if (lastDate == null) {
            return stockPriceFetcher.fetchStockPrices(stockId, null);
        }
        return stockPriceFetcher.fetchStockPrices(stockId, lastDate.plusDays(1));
    }
}
