package com.gyeryongbrother.pickandtest.stock.infrastructure.api.digrin.common;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DigrinStockExchange {

    NYQ(StockExchange.NYSE),
    NCM(StockExchange.NASDAQ),
    NGM(StockExchange.NASDAQ),
    NMS(StockExchange.NASDAQ),
    NAS(StockExchange.NASDAQ),
    KSC(StockExchange.KOSPI),
    KOE(StockExchange.KOSDAQ),
    ;

    private final StockExchange stockExchange;

    public static List<DigrinStockExchange> digrinStockExchangesFrom(StockExchange stockExchange) {
        return Arrays.stream(values())
                .filter(it -> it.stockExchange.equals(stockExchange))
                .toList();
    }
}
