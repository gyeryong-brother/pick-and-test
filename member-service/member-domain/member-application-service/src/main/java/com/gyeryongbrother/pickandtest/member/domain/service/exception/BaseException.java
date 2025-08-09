package com.gyeryongbrother.pickandtest.member.domain.service.exception;

public abstract class BaseException extends RuntimeException {

    public abstract BaseExceptionType exceptionType();
}
