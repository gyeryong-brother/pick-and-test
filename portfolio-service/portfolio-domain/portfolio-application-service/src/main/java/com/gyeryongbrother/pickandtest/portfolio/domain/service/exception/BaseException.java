package com.gyeryongbrother.pickandtest.portfolio.domain.service.exception;

public abstract class BaseException extends RuntimeException {

    public abstract BaseExceptionType exceptionType();
}
