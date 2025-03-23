package com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.stockprice.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockPriceInfrastructureException extends BaseException {

    private final StockPriceInfrastructureExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
