package com.gyeryongbrother.pickandtest.stock.domain.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockServiceException extends BaseException{

    private final StockServiceExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
