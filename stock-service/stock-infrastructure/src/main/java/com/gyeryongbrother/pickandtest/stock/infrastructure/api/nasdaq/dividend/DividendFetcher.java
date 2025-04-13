package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto.DividendResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DividendFetcher {

    private final NasdaqUrlProvider nasdaqUrlProvider;
    private final HeaderProvider headerProvider;
    private final FetcherSupport fetcherSupport;

    public DividendResponse fetchDividend(String symbol, String assetClass) {
        URI uri = nasdaqUrlProvider.getDividendEndpoint(symbol, assetClass);
        HttpHeaders httpHeaders = headerProvider.getHeaders();
        return fetcherSupport.get(uri, httpHeaders, DividendResponse.class)
                .getBody();
    }
}
