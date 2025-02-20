package com.gyeryongbrother.pickandtest.member.domain.service.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberServiceExceptionType implements BaseExceptionType {
    USER_ID_EXISTS(NOT_FOUND, "이미 존재하는 아이디입니다."),
    INCORRECT_PASSWORD(NOT_FOUND, "비밀번호가 일치하지 않습니다."),
    USER_NONEXISTS(NOT_FOUND, "존재하지 않는 아이디입니다.");

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
