package com.gyeryongbrother.pickandtest.portfolio.dataaccess.exception;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PortfolioException extends BaseException {

    private final PortfolioExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
