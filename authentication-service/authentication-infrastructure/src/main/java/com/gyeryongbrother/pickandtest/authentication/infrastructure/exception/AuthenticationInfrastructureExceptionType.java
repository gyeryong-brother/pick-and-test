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
    INVALID_TOKEN_ERROR(UNAUTHORIZED, "Invalid Token Error"),
    OAUTH_SERVER_NOT_SUPPORTED(BAD_REQUEST, "지원하지 않는 서버 타입입니다."),
    REQUEST_CONTEXT_ERROR(INTERNAL_SERVER_ERROR, "등록되지 않은 키입니다."),
    REQUEST_TYPE_ERROR(INTERNAL_SERVER_ERROR, "지원하지 않는 타입입니다."),
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
