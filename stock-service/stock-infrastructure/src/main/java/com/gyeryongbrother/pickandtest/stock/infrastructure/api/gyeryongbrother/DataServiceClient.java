package com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.common.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.dto.TickersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataServiceClient {

    private final DataServiceUrlProvider dataServiceUrlProvider;
    private final FetcherSupport fetcherSupport;

    public StockResponse fetchStock(String symbol) {
        String url = dataServiceUrlProvider.getStockEndpoint(symbol);
        return fetcherSupport.get(url, StockResponse.class);
    }

    public TickersResponse fetchTickers(String exchange) {
        String url = dataServiceUrlProvider.getTickersEndpoint(exchange);
        return fetcherSupport.get(url, TickersResponse.class);
    }
}
