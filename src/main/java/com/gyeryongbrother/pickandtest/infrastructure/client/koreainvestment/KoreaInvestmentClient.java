package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFetcher;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockInformation;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreaInvestmentClient {

    private final StockFetcher stockFetcher;

    public Stock fetchStock(String symbol) {
        FetchStockResponse fetchStockResponse = stockFetcher.fetchStock(StockExchange.NASDAQ, symbol);
        StockInformation stockInformation = fetchStockResponse.stockInformation();
        return Stock.builder()
                .name(stockInformation.productEnglishName())
                .symbol(symbol)
                .listingDate(parse(stockInformation.listingDate()))
                .build();
    }

    private LocalDate parse(String listingDate) {
        if (listingDate.isBlank()) {
            return null;
        }
        return LocalDate.parse(listingDate);
    }
}
