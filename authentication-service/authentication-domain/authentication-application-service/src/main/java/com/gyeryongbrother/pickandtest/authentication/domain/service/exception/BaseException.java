package com.gyeryongbrother.pickandtest.authentication.domain.service.exception;

public abstract class BaseException extends RuntimeException {

    public abstract BaseExceptionType exceptionType();
}
