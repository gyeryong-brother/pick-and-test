package com.gyeryongbrother.pickandtest.authentication.infrastructure.exception;

import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationInfrastructureException extends BaseException {

    private final AuthenticationInfrastructureExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
