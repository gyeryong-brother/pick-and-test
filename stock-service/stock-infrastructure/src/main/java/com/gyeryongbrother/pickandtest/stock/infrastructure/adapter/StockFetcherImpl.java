package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.client.StockClient;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockFetcherImpl implements StockFetcher {

    private final StockClient stockClient;

    @Override
    public Optional<Stock> fetchStock(StockExchange stockExchange, String symbol) {
        return stockClient.fetchStock(stockExchange, symbol);
    }
}
