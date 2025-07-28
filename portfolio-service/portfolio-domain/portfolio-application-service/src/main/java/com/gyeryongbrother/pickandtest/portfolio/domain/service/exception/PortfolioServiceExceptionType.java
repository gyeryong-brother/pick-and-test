package com.gyeryongbrother.pickandtest.portfolio.domain.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum PortfolioServiceExceptionType implements BaseExceptionType{
    INVALID_USER(HttpStatus.FORBIDDEN,"잘못된 사용자입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    @Override
    public HttpStatus httpStatus() {return httpStatus;}

    @Override
    public String errorMessage() {return errorMessage;}
}
