package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.StockSymbolFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NasdaqClient {

    private final StockSymbolFetcher stockSymbolFetcher;

    public StockSymbolResponse fetchStockSymbol(StockExchange stockExchange) {
        return stockSymbolFetcher.fetchStockSymbol(stockExchange);
    }
}
