package com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception;

public abstract class BaseException extends RuntimeException {

    public abstract BaseExceptionType exceptionType();
}
