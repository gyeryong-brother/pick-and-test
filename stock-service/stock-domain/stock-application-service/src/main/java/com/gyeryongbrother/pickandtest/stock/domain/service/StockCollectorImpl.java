package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.Symbols;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.StockCollector;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockFetcher;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.SymbolFetcher;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.message.publisher.StockMessagePublisher;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockCollectorImpl implements StockCollector {

    private final StockRepository stockRepository;
    private final StockQueryRepository stockQueryRepository;
    private final SymbolFetcher symbolFetcher;
    private final StockFetcher stockFetcher;
    private final StockMessagePublisher stockMessagePublisher;

    @Override
    public void collectStocks() {
        collectStocks(StockExchange.NASDAQ);
        collectStocks(StockExchange.NYSE);
    }

    private void collectStocks(StockExchange stockExchange) {
        List<String> existSymbols = stockQueryRepository.findAllSymbolsByStockExchange(stockExchange);
        List<String> fetchedSymbols = symbolFetcher.fetchSymbol(stockExchange);
        log.info("fetched symbols size: {}", fetchedSymbols.size());
        Symbols symbols = new Symbols(fetchedSymbols);
        symbols.removeAll(existSymbols);
        symbols.values().forEach(it -> collectStock(stockExchange, it));
    }

    private void collectStock(StockExchange stockExchange, String symbol) {
        log.info("fetch stock started. stockExchange: {}, symbol: {}", stockExchange, symbol);
        Optional<Stock> stock = stockFetcher.fetchStock(stockExchange, symbol);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (stock.isEmpty()) {
            return;
        }
        Stock savedStock = stockRepository.save(stock.get());
        stockMessagePublisher.publishStockCreatedEvent(savedStock);
    }
}
