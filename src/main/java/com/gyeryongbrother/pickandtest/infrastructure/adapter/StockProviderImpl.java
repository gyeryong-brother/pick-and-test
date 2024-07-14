package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockInformation;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockProviderImpl implements StockProvider {

    private final KoreaInvestmentClient koreaInvestmentClient;

    @Override
    public Stock provide(String symbol) {
        FetchStockResponse fetchStockResponse = koreaInvestmentClient.fetchStock(StockExchange.NASDAQ, symbol);
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
