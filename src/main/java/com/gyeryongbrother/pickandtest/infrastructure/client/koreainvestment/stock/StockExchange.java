package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StockExchange {

    NASDAQ("512"),
    NYSE("513"),
    AMEX("529"),
    ;

    private final String code;
}
