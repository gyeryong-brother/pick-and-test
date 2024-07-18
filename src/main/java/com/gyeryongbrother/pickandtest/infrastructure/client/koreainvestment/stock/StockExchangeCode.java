package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.AMEX;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NYSE;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StockExchangeCode {

    NASDAQ_CODE("512", "NAS"),
    NYSE_CODE("513", "NYS"),
    AMEX_CODE("529", "AMS"),
    ;

    private final String productTypeCode;
    private final String exchangeCode;

    public static StockExchangeCode from(StockExchange stockExchange) {
        if (stockExchange == NASDAQ) {
            return NASDAQ_CODE;
        }
        if (stockExchange == NYSE) {
            return NYSE_CODE;
        }
        if (stockExchange == AMEX) {
            return AMEX_CODE;
        }
        throw new IllegalArgumentException();
    }
}
