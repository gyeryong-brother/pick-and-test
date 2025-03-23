package com.gyeryongbrother.pickandtest.stockprice.domain.service;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.input.StockPriceCollector;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceClient;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockPriceCollectorImpl implements StockPriceCollector {

    private final StockPriceQueryRepository stockPriceQueryRepository;
    private final StockPriceClient stockPriceClient;

    @Override
    public void collectStockPrices(Long stockId) {
        List<StockPrice> stockPrices = stockPriceQueryRepository.findAllByStockId(stockId);
        List<StockPrice> fetchedStockPrices = stockPriceClient.fetchStockPrices(stockId);
    }
}
