package com.gyeryongbrother.pickandtest.stock.domain.core.valueobject;

import static com.gyeryongbrother.pickandtest.stock.domain.core.exception.StockCoreExceptionType.NO_EXIST_STOCK_EXCHANGE;

import com.gyeryongbrother.pickandtest.stock.domain.core.exception.StockCoreException;
import java.util.Arrays;

public enum StockExchange {

    NYQ,
    NCM,
    NGM,
    NMS,
    NAS,
    KSC,
    KOE,
    ;

    public static StockExchange from(String stockExchange) {
        String trimmed = stockExchange.trim();
        return Arrays.stream(StockExchange.values())
                .filter(it -> it.name().equalsIgnoreCase(trimmed))
                .findAny()
                .orElseThrow(() -> new StockCoreException(NO_EXIST_STOCK_EXCHANGE));
    }
}
