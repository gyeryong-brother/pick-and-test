package com.gyeryongbrother.pickandtest.stockprice.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockPriceCoreException extends CoreException {

    private final StockPriceCoreExceptionType exceptionType;

    @Override
    public CoreExceptionType exceptionType() {
        return exceptionType;
    }
}
