package com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockStockPriceFetcher implements StockPriceFetcher {

    private final Map<Long, List<StockPrice>> stockPricesByStockId;

    @Override
    public List<StockPrice> fetchStockPrices(Long stockId, LocalDate startDate) {
        if (stockPricesByStockId.containsKey(stockId)) {
            return filter(stockId, startDate);
        }
        return List.of();
    }

    private List<StockPrice> filter(Long stockId, LocalDate startDate) {
        if (startDate == null) {
            return stockPricesByStockId.get(stockId);
        }
        return stockPricesByStockId.get(stockId).stream()
                .filter(it -> !it.date().isBefore(startDate))
                .toList();
    }
}
