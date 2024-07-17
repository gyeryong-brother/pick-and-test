package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq;

import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.StockExchangeFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockExchangeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NasdaqClient {

    private final StockExchangeFetcher stockExchangeFetcher;

    public StockExchangeResponse fetchStockExchange(StockExchange stockExchange) {
        return stockExchangeFetcher.fetchStockExchange(stockExchange);
    }
}
