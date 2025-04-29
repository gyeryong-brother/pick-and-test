package com.gyeryongbrother.pickandtest.authentication.domain.core.exception;

public abstract class CoreException extends RuntimeException {

    public abstract CoreExceptionType exceptionType();
}
