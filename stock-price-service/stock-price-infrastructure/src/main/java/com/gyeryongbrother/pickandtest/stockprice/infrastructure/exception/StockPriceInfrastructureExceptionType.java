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
    NOT_SUPPORTED_STOCK_EXCHANGE(INTERNAL_SERVER_ERROR, "지원되지 않는 거래소입니다"),
    INVALID_CONTINUITY_CODE(INTERNAL_SERVER_ERROR, "유효하지 않은 지속 코드입니다"),
    HEADER_CAN_NOT_BE_NULL(INTERNAL_SERVER_ERROR, "응답 헤더는 널일 수 없습니다"),
    HEADER_SHOULD_HAVE_ONLY_ONE_VALUE(INTERNAL_SERVER_ERROR, "헤더는 하나의 값만을 가져야 합니다"),
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
