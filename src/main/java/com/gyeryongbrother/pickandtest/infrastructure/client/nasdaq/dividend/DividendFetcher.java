package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend;

import com.gyeryongbrother.pickandtest.infrastructure.client.FetcherSupport;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
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
