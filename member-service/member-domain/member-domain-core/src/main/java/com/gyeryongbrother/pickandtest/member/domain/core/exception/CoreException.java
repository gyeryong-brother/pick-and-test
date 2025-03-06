package com.gyeryongbrother.pickandtest.member.domain.core.exception;

public abstract class CoreException extends RuntimeException {

    public abstract CoreExceptionType exceptionType();
}
