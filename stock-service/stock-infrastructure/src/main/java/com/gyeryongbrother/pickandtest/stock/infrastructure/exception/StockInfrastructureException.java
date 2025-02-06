package com.gyeryongbrother.pickandtest.stock.infrastructure.exception;

import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockInfrastructureException extends BaseException {

    private final StockInfrastructureExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
