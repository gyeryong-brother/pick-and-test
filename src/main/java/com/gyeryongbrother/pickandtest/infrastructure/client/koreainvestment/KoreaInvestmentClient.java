package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.Period;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final StockFetcher stockFetcher;
    private final StockPriceFetcher stockPriceFetcher;

    public FetchStockResponse fetchStock(StockExchange stockExchange, String symbol) {
        return stockFetcher.fetchStock(stockExchange, symbol);
    }

    public StockPriceResponse fetchStockPrice(
            StockExchange stockExchange,
            String symbol,
            LocalDate date
    ) {
        return stockPriceFetcher.fetchStockPrice(stockExchange, symbol, Period.DAY, date);
    }
}
