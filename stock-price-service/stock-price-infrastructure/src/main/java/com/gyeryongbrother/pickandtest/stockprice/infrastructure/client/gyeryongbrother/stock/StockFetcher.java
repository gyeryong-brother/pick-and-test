package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.common.StockServiceUrlProvider;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcher {

    private final StockServiceUrlProvider stockServiceUrlProvider;
    private final FetcherSupport fetcherSupport;

    public StockResponse fetchStock(Long stockId) {
        String url = stockServiceUrlProvider.getStockEndpoint(stockId);
        return fetcherSupport.get(url, StockResponse.class);
    }
}
