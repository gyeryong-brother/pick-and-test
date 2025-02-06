package com.gyeryongbrother.pickandtest.portfolio.dataaccess.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum PortfolioExceptionType implements BaseExceptionType {

    PORTFOLIO_NOT_FOUND(NOT_FOUND, "포트폴리오가 존재하지 않습니다"),
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
