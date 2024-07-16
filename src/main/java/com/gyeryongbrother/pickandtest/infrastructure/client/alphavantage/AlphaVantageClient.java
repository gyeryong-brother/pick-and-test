package com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage;

import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.DividendFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlphaVantageClient {

    private final DividendFetcher dividendFetcher;

    public DividendResponse fetchDividend(String symbol) {
        return dividendFetcher.fetchDividend(symbol);
    }
}
