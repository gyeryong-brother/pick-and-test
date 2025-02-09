package com.gyeryongbrother.pickandtest.stock.domain.core.exception;

public abstract class CoreException extends RuntimeException {

    public abstract CoreExceptionType exceptionType();
}
