package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.StockExchangeFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stockexchange.dto.StockSymbolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NasdaqClient {

    private final StockExchangeFetcher stockExchangeFetcher;

    public StockSymbolResponse fetchStockSymbol(StockExchange stockExchange) {
        return stockExchangeFetcher.fetchStockSymbol(stockExchange);
    }
}
