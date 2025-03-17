package com.gyeryongbrother.pickandtest.stockprice.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StockPriceCoreExceptionType implements CoreExceptionType {

    INVALID_YEAR("invalid year"),
    ;

    private final String errorMessage;

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
