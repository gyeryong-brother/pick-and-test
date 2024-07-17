package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StockExchange {

    NASDAQ("512", "NAS"),
    NYSE("513", "NYS"),
    AMEX("529", "AMS"),
    ;

    private final String productTypeCode;
    private final String exchangeCode;
}
