package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.dto.StockSymbolResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
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
