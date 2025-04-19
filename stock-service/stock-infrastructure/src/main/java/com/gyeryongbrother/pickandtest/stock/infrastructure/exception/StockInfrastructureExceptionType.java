package com.gyeryongbrother.pickandtest.stock.infrastructure.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum StockInfrastructureExceptionType implements BaseExceptionType {

    API_FETCH_CLIENT_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    API_FETCH_SERVER_ERROR(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    API_FETCH_FAILED(INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다"),
    BODY_READ_FAILED(INTERNAL_SERVER_ERROR, "body 를 읽는데 실패했습니다"),
    NOT_SUPPORTED_STOCK_EXCHANGE(INTERNAL_SERVER_ERROR, "지원되지 않는 거래소입니다"),
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
