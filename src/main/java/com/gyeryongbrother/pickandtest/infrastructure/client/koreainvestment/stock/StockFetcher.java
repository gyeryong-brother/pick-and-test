package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.UrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockFetcher {

    private final UrlProvider urlProvider;
    private final HeaderHandler headerHandler;
    private final FetcherSupport fetcherSupport;

    public FetchStockResponse fetchStock(StockExchange stockExchange, String symbol) {
        String url = urlProvider.getStockEndpoint(stockExchange, symbol);
        HttpHeaders httpHeaders = headerHandler.getHeader(FetchType.STOCK);
        return fetcherSupport.get(url, httpHeaders, FetchStockResponse.class)
                .getBody();
    }
}
