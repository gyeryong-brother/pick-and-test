package com.gyeryongbrother.pickandtest.stockprice.domain.service.exception;

public abstract class BaseException extends RuntimeException {

    public abstract BaseExceptionType exceptionType();
}
