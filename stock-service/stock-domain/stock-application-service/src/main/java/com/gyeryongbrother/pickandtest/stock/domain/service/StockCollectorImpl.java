package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.Symbols;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockCollector;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.*;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockCollectorImpl implements StockCollector {

    private final StockRepository stockRepository;
    private final StockQueryRepository stockQueryRepository;
    private final SymbolFetcher symbolFetcher;
    private final StockFetcher stockFetcher;
    private final OutboxRepository outboxRepository;

    @Override
    public void collectStocks() {
        collectStocks(StockExchange.NGM);
    }

    private void collectStocks(StockExchange stockExchange) {
        List<String> existSymbols = stockQueryRepository.findAllSymbolsByStockExchange(stockExchange);
        List<String> fetchedSymbols = symbolFetcher.fetchSymbols(stockExchange).subList(0, 50);
        log.info("fetched symbols size: {}", fetchedSymbols.size());
        Symbols symbols = Symbols.from(fetchedSymbols);
        symbols.removeAll(existSymbols);
        symbols.values().forEach(this::collectStock);
    }

    @Transactional
    public void collectStock(String symbol) {
        log.info("Start fetch stock. symbol: {}", symbol);
        Optional<Stock> stock = stockFetcher.fetchStock(symbol);
        log.info("End fetch stock. symbol: {}", symbol);
        stock.ifPresent(s -> {
            stockRepository.save(s);
            outboxRepository.saveStockCreated(s);
        });
    }
}
