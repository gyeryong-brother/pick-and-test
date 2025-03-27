package com.gyeryongbrother.pickandtest.stockprice.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.ports.output.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.StockServiceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.StockPriceClient;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockPriceFetcherImpl implements StockPriceFetcher {

    private final StockServiceClient stockServiceClient;
    private final StockPriceClient stockPriceClient;

    @Override
    public List<StockPrice> fetchStockPrices(Long stockId, LocalDate startDate) {
        Stock stock = stockServiceClient.fetchStock(stockId);
        return stockPriceClient.fetchStockPrices(stock, startDate);
    }
}
