package com.gyeryongbrother.pickandtest.authentication.domain.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationServiceException extends BaseException {

    private final AuthenticationServiceExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
