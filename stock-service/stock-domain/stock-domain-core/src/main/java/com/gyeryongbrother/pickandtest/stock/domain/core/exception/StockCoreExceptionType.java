package com.gyeryongbrother.pickandtest.stock.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StockCoreExceptionType implements CoreExceptionType {

    INVALID_YEAR("invalid year"),
    ;

    private final String errorMessage;

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
