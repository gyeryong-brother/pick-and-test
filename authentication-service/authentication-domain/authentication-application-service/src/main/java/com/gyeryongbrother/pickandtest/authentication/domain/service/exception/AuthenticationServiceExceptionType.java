package com.gyeryongbrother.pickandtest.authentication.domain.service.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthenticationServiceExceptionType implements BaseExceptionType {

    USERNAME_ALREADY_EXISTS(BAD_REQUEST, "이미 존재하는 아이디입니다."),
    REFRESH_TOKEN_NOT_REGISTERED(NOT_FOUND, "등록되지 않은 리프레시 토큰입니다."),
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
