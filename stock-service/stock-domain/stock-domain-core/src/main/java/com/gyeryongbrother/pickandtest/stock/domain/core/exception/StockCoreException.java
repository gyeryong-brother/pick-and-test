package com.gyeryongbrother.pickandtest.stock.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockCoreException extends CoreException {

    private final StockCoreExceptionType exceptionType;

    @Override
    public CoreExceptionType exceptionType() {
        return exceptionType;
    }
}
