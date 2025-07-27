package com.gyeryongbrother.pickandtest.stockprice.domain.core.exception;

public abstract class CoreException extends RuntimeException {

    public abstract CoreExceptionType exceptionType();
}
