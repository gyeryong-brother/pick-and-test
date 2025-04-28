package com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum StockPriceInfrastructureExceptionType implements BaseExceptionType {

    API_FETCH_CLIENT_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    API_FETCH_SERVER_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    API_FETCH_FAILED(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
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
