package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Period {

    DAY("0"),
    WEEK("1"),
    MONTH("2"),
    ;

    private final String code;

    public String code() {
        return code;
    }
}
