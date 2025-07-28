package com.gyeryongbrother.pickandtest.portfolio.domain.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PortfolioServiceException extends BaseException {

    private final PortfolioServiceExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
