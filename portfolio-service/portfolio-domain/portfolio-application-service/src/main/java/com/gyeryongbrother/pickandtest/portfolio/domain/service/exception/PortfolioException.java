package com.gyeryongbrother.pickandtest.portfolio.domain.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PortfolioException extends BaseException {

    private final PortfolioExceptionType exceptionType;

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
