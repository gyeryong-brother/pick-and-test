package com.gyeryongbrother.pickandtest.authentication.domain.core.exception;

public enum AuthenticationCoreExceptionType implements CoreExceptionType {

    AUTHENTICATION_METHOD_NOT_MATCHED("authentication method not matched"),
    INVALID_USERROLE("잘못된 사용자 정보입니다."),
    ;

    private final String errorMessage;

    AuthenticationCoreExceptionType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
