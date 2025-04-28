package com.gyeryongbrother.pickandtest.stock.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StockCoreExceptionType implements CoreExceptionType {

    INVALID_YEAR("invalid year"),
    NO_EXIST_STOCK_EXCHANGE("no exist stock exchange"),
    ;

    private final String errorMessage;

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
