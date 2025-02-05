package com.gyeryongbrother.pickandtest.portfolio.domain.service.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PortfolioExceptionType implements BaseExceptionType {

    PORTFOLIO_NOT_FOUND(NOT_FOUND, "포트폴리오가 존재하지 않습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
