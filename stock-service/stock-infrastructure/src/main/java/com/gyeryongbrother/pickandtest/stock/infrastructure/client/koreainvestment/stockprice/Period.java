package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Period {

    DAY("0"),
    WEEK("1"),
    MONTH("2"),
    ;

    private final String code;
}
