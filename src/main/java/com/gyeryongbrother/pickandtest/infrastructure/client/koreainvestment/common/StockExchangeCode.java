package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.*;

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
        throw new IllegalArgumentException("not supported stock exchange");
    }
}
