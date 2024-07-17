package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockExchangeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockExchangeFetcher {

    private final NasdaqUrlProvider nasdaqUrlProvider;
    private final HeaderProvider headerProvider;
    private final FetcherSupport fetcherSupport;

    public StockExchangeResponse fetchStockExchange(StockExchange stockExchange) {
        String url = nasdaqUrlProvider.getStockExchangeEndpoint(stockExchange);
        HttpHeaders httpHeaders = headerProvider.getHeaders();
        return fetcherSupport.get(url, httpHeaders, StockExchangeResponse.class)
                .getBody();
    }
}
