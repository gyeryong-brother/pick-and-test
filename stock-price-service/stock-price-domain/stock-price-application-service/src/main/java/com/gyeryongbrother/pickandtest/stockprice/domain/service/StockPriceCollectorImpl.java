package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceClient;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockPriceCollectorImpl implements StockPriceCollector {

    private final StockPriceQueryRepository stockPriceQueryRepository;
    private final StockPriceClient stockPriceClient;

    @Override
    public void collectStockPrices(Stock stock) {
        List<StockPrice> stockPrices = stockPriceQueryRepository.findAllByStockId(stock.id());
        List<StockPrice> fetchedStockPrices = stockPriceClient.fetchStockPrices(stock.id(), LocalDate.of(2025, 1, 1));
    }
}
