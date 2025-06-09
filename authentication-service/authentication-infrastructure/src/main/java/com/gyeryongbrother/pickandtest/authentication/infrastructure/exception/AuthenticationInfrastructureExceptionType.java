package com.gyeryongbrother.pickandtest.authentication.infrastructure.exception;

import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthenticationInfrastructureExceptionType implements BaseExceptionType {

    USERNAME_PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "사용자 정보가 일치하지 않습니다."),
    INVALID_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "Invalid Token Error"),
    OAUTH_SERVER_NOT_SUPPORTED(HttpStatus.BAD_REQUEST, "지원하지 않는 서버 타입입니다."),
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
