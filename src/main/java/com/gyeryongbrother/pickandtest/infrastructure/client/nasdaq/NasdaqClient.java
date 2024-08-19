package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.DividendFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.StockSymbolFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NasdaqClient {

    private final StockSymbolFetcher stockSymbolFetcher;
    private final DividendFetcher dividendFetcher;

    public StockSymbolResponse fetchStockSymbol(StockExchange stockExchange) {
        return stockSymbolFetcher.fetchStockSymbol(stockExchange);
    }

    public DividendResponse fetchDividend(String symbol, String assetClass) {
        return dividendFetcher.fetchDividend(symbol, assetClass);
    }
}
