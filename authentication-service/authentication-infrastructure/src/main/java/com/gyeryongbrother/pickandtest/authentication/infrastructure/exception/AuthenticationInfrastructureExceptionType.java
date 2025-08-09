package com.gyeryongbrother.pickandtest.authentication.infrastructure.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthenticationInfrastructureExceptionType implements BaseExceptionType {

    API_FETCH_CLIENT_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    API_FETCH_SERVER_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    API_FETCH_FAILED(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    USERNAME_PASSWORD_NOT_MATCH(BAD_REQUEST, "사용자 정보가 일치하지 않습니다."),
    OAUTH_SERVER_NOT_SUPPORTED(BAD_REQUEST, "지원하지 않는 서버 타입입니다."),
    LOGIN_TYPE_NOT_SUPPORTED(BAD_REQUEST, "지원하지 않는 로그인 타입입니다."),
    CREATE_BODY_FAILED(INTERNAL_SERVER_ERROR, "응답을 만드는데 실패했습니다."),
    READ_BODY_FAILED(INTERNAL_SERVER_ERROR, "본문을 읽는데 실패했습니다."),
    REDIRECT_FAILED(INTERNAL_SERVER_ERROR, "리다이렉트 하는데 실패했습니다."),
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
