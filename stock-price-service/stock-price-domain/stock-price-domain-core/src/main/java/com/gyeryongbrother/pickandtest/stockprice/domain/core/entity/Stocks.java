package com.gyeryongbrother.pickandtest.stockprice.domain.core.entity;

import static java.util.function.Function.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stocks {

    private final Map<String, Stock> stocksBySymbol;

    public Stocks(List<Stock> stocks) {
        stocksBySymbol = stocks.stream()
                .collect(Collectors.toMap(Stock::symbol, identity()));
    }

    public List<String> symbols() {
        return new ArrayList<>(stocksBySymbol.keySet());
    }

    public Long stockIdBySymbol(String symbol) {
        return Optional.ofNullable(stocksBySymbol.get(symbol))
                .map(Stock::id)
                .orElseThrow(() -> new IllegalArgumentException("Stock not found for symbol: " + symbol));
    }
}
