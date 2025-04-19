package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.SymbolFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.DataServiceClient;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.gyeryongbrother.dto.TickersResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymbolFetcherImpl implements SymbolFetcher {

    private final DataServiceClient dataServiceClient;

    @Override
    public List<String> fetchSymbols(StockExchange stockExchange) {
        String exchange = stockExchange.name().toLowerCase();
        TickersResponse tickersResponse = dataServiceClient.fetchTickers(exchange);
        return tickersResponse.tickers();
    }
}
