package com.gyeryongbrother.pickandtest.stock.dataaccess.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum StockDataExceptionType implements BaseExceptionType {

    FAVORITE_STOCK_NOT_FOUND(NOT_FOUND, "관심 주식이 존재하지 않습니다"),
    STOCK_NOT_FOUND(NOT_FOUND, "주식이 존재하지 않습니다"),
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
