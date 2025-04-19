package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.common.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockServiceClient {

    private final StockServiceUrlProvider stockServiceUrlProvider;
    private final FetcherSupport fetcherSupport;

    public Stock fetchStock(Long stockId) {
        String url = stockServiceUrlProvider.getStockEndpoint(stockId);
        StockResponse stock = fetcherSupport.get(url, StockResponse.class);
        return new Stock(
                stockId,
                stock.symbol(),
                StockExchange.valueOf(stock.stockExchange())
        );
    }
}
