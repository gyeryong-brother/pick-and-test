package com.gyeryongbrother.pickandtest.member.domain.service.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberServiceExceptionType implements BaseExceptionType {
    USER_ID_EXISTS(BAD_REQUEST, "이미 존재하는 아이디입니다."),
    INCORRECT_PASSWORD(NOT_FOUND, "사용자 정보가 일치하지 않습니다."),
    USER_NONEXISTS(NOT_FOUND, "사용자 정보가 일치하지 않습니다."),
    CORE_EXCEPTION_SHOULD_BE_REGISTERED(NOT_FOUND, "코어 예외가 존재하지 않습니다."),
    CORE_EXCEPTION_NOT_REGISTERED(NOT_FOUND, "코어 예외가 존재하지 않습니다."),
    USERROLE_NONEXISTS(NOT_FOUND, "존재하지 않는 사용자 역할입니다."),
    INVALID_REFRESH_TOKEN(NOT_FOUND, "Invalid Refresh Token Error");

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
