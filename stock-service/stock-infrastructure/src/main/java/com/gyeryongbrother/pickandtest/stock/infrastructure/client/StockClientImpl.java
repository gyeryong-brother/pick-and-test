package com.gyeryongbrother.pickandtest.stock.infrastructure.client;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.dto.StockDetail;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.stock.dto.StockResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockClientImpl implements StockClient {

    private final KoreaInvestmentClient koreaInvestmentClient;

    @Override
    public Optional<Stock> fetchStock(StockExchange stockExchange, String symbol) {
        StockResponse stockResponse = koreaInvestmentClient.fetchStock(stockExchange, symbol);
        StockDetail stockDetail = stockResponse.stockDetail();
        return getStock(stockExchange, symbol, stockDetail);
    }

    private Optional<Stock> getStock(StockExchange stockExchange, String symbol, StockDetail stockDetail) {
        if (stockDetail == null) {
            return Optional.empty();
        }
        Stock stock = Stock.builder()
                .name(stockDetail.productEnglishName())
                .symbol(symbol)
                .stockExchange(stockExchange)
                .outstandingShares(getOutstandingShares(stockDetail.listingStockNumber()))
                .listingDate(null)
                .build();
        return Optional.of(stock);
    }

    private Long getOutstandingShares(String listingStockNumber) {
        if (listingStockNumber.isBlank()) {
            return null;
        }
        return Long.valueOf(listingStockNumber);
    }
}
