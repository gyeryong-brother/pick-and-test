package com.gyeryongbrother.pickandtest.stock.domain.service.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum StockServiceExceptionType implements BaseExceptionType {

    CAN_NOT_DELETE_FAVORITE_STOCK(FORBIDDEN, "관심 주식을 삭제할 권한이 없습니다"),
    STOCK_IS_ALREADY_LIKED(BAD_REQUEST, "이미 관심 주식으로 등록한 주식입니다"),
    CAN_NOT_CALCULATE_COMPOUND_ANNUAL_GROWTH_RATE(INTERNAL_SERVER_ERROR, "연평균 성장률을 계산할 수 없습니다"),
    CORE_EXCEPTION_SHOULD_BE_REGISTERED(INTERNAL_SERVER_ERROR, "모든 도메인 예외는 등록되어야 합니다"),
    CORE_EXCEPTION_NOT_REGISTERED(INTERNAL_SERVER_ERROR, "등록되지 않은 도메인 예외가 발생했습니다"),
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
