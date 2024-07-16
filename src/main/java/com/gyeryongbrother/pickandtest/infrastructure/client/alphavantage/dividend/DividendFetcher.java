package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.common.AlphaVantageUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DividendFetcher {

    private final AlphaVantageUrlProvider alphaVantageUrlProvider;
    private final FetcherSupport fetcherSupport;

    public DividendResponse fetchDividend(String symbol) {
        String url = alphaVantageUrlProvider.getDividendEndpoint(symbol);
        return fetcherSupport.get(url, DividendResponse.class);
    }
}
