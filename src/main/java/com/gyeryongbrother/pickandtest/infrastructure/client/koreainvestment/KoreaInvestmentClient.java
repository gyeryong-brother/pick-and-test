package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final StockFetcher stockFetcher;

    public FetchStockResponse fetchStock(StockExchange stockExchange, String symbol) {
        return stockFetcher.fetchStock(stockExchange, symbol);
    }
}
