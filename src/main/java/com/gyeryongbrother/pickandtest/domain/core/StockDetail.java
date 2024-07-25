package com.gyeryongbrother.pickandtest.domain.core;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class StockDetail {

    private final Stock stock;
    private final List<StockPrice> stockPrices;
    private final List<Dividend> dividends;

    public List<MarketCapitalization> getMarketCapitalizations() {
        return stockPrices.stream()
                .map(stock::calculateMarketCapitalization)
                .toList();
    }
}
