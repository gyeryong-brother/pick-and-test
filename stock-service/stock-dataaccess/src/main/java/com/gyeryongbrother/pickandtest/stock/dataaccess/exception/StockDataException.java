package com.gyeryongbrother.pickandtest.stock.dataaccess.exception;

import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockDataException extends BaseException {

    private final StockDataExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
