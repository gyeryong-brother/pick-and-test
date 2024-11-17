package com.gyeryongbrother.pickandtest.stock.domain.core.entity;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.MarketCapitalization;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class StockWithPrices {

    private final Stock stock;
    private final List<StockPrice> stockPrices;

    public List<MarketCapitalization> getMarketCapitalizations() {
        return stockPrices.stream()
                .map(stock::calculateMarketCapitalization)
                .toList();
    }
}
