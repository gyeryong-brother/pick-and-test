package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockSymbolFetcher {

    private final NasdaqUrlProvider nasdaqUrlProvider;
    private final HeaderProvider headerProvider;
    private final FetcherSupport fetcherSupport;

    public StockSymbolResponse fetchStockSymbol(StockExchange stockExchange) {
        String url = nasdaqUrlProvider.getStockSymbolEndpoint(stockExchange);
        HttpHeaders httpHeaders = headerProvider.getHeaders();
        return fetcherSupport.get(url, httpHeaders, StockSymbolResponse.class)
                .getBody();
    }
}
