package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.Period.DAY;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
