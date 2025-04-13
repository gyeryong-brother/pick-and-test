package com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.dto.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final StockFetcher stockFetcher;

    public StockResponse fetchStock(StockExchange stockExchange, String symbol) {
        StockExchangeCode stockExchangeCode = StockExchangeCode.from(stockExchange);
        return stockFetcher.fetchStock(stockExchangeCode, symbol);
    }
}
