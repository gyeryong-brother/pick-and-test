package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.Period.DAY;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common.StockExchangeCode;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.StockPriceFetcher;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponse;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final StockPriceFetcher stockPriceFetcher;

    public StockPriceResponse fetchStockPrice(
            StockExchange stockExchange,
            String symbol,
            LocalDate date
    ) {
        StockExchangeCode stockExchangeCode = StockExchangeCode.from(stockExchange);
        return stockPriceFetcher.fetchStockPrice(stockExchangeCode, symbol, DAY, date);
    }
}
