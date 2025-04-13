package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.SymbolFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.digrin.DigrinClient;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.digrin.common.DigrinStockExchange;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymbolFetcherImpl implements SymbolFetcher {

    private final DigrinClient digrinClient;

    @Override
    public List<String> fetchSymbol(StockExchange stockExchange) {
        List<DigrinStockExchange> digrinStockExchanges = DigrinStockExchange.digrinStockExchangesFrom(stockExchange);
        List<String> symbols = new ArrayList<>();
        for (DigrinStockExchange digrinStockExchange : digrinStockExchanges) {
            List<String> fetchedSymbols = digrinClient.fetchSymbols(digrinStockExchange);
            symbols.addAll(fetchedSymbols);
        }
        return symbols;
    }
}
