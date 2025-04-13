package com.gyeryongbrother.pickandtest.member.infrastructure.exception;

import com.gyeryongbrother.pickandtest.member.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberInfrastructureExceptionType implements BaseExceptionType {
    INVALID_TOKEN_ERROR(HttpStatus.UNAUTHORIZED,"Invalid Token Error")
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
