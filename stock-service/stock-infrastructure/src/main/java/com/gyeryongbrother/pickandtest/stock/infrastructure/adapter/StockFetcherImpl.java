package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.exception.StockCoreException;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.DataServiceClient;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.dto.StockResponse;
import com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockFetcherImpl implements StockFetcher {

    private final DataServiceClient dataServiceClient;

    @Override
    public Optional<Stock> fetchStock(String symbol) {
        try {
            return fetchStockBySymbol(symbol);
        } catch (StockCoreException | StockInfrastructureException e) {
            log.error("fetch failed. message: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Stock> fetchStockBySymbol(String symbol) {
        StockResponse stockResponse = dataServiceClient.fetchStock(symbol);
        if (stockResponse == null || hasNotRequiredFields(stockResponse)) {
            return Optional.empty();
        }
        return Optional.of(toStock(stockResponse));
    }

    private boolean hasNotRequiredFields(StockResponse stockResponse) {
        return isNull(stockResponse.name()) ||
                isNull(stockResponse.symbol()) ||
                isNull(stockResponse.stockExchange());
    }

    private boolean isNull(String field) {
        return field == null;
    }

    private Stock toStock(StockResponse stockResponse) {
        return Stock.builder()
                .name(stockResponse.name())
                .symbol(stockResponse.symbol())
                .stockExchange(StockExchange.from(stockResponse.stockExchange()))
                .outstandingShares(toOutstandingShares(stockResponse.outstandingShares()))
                .build();
    }

    private Long toOutstandingShares(String outstandingShares) {
        if (outstandingShares == null) {
            return null;
        }
        return Long.valueOf(outstandingShares);
    }
}
