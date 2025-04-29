package com.gyeryongbrother.pickandtest.authentication.domain.core.exception;

public class AuthenticationCoreException extends CoreException{

    private final AuthenticationCoreExceptionType exceptionType;

    public AuthenticationCoreException(AuthenticationCoreExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public CoreExceptionType exceptionType() {
        return exceptionType;
    }
}
