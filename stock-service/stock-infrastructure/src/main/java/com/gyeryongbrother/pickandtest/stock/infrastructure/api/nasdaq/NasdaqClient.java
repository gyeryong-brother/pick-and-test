package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.DividendFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.StockSymbolFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.stocksymbol.dto.StockSymbolResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
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
