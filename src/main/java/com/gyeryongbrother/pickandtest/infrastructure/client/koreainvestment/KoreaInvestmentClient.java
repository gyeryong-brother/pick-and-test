package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.Period.DAY;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final StockFetcher stockFetcher;
    private final StockPriceFetcher stockPriceFetcher;

    public StockResponse fetchStock(StockExchange stockExchange, String symbol) {
        StockExchangeCode stockExchangeCode = StockExchangeCode.from(stockExchange);
        return stockFetcher.fetchStock(stockExchangeCode, symbol);
    }

    public StockPriceResponse fetchStockPrice(
            StockExchange stockExchange,
            String symbol,
            LocalDate date
    ) {
        StockExchangeCode stockExchangeCode = StockExchangeCode.from(stockExchange);
        return stockPriceFetcher.fetchStockPrice(stockExchangeCode, symbol, DAY, date);
    }
}
