package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.HeaderProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.UrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcher {

    private final UrlProvider urlProvider;
    private final HeaderProvider headerProvider;
    private final FetcherSupport fetcherSupport;

    public FetchStockResponse fetchStock(StockExchange stockExchange, String symbol) {
        String url = urlProvider.getStockEndpoint(stockExchange, symbol);
        HttpHeaders httpHeaders = headerProvider.getStockHeader();
        return fetcherSupport.get(url, httpHeaders, FetchStockResponse.class);
    }
}
