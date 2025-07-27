package com.gyeryongbrother.pickandtest.dividend.infrastructure.exception;

import com.gyeryongbrother.pickandtest.dividend.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.dividend.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DividendInfrastructureException extends BaseException {

    private final DividendInfrastructureExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
