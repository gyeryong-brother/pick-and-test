package com.gyeryongbrother.pickandtest.stock.application.exception.handler.dto;

public record ErrorResponse(
        String errorMessage
) {

    public static ErrorResponse INTERNAL_SERVER_ERROR_RESPONSE = new ErrorResponse("알 수 없는 오류가 발생했습니다.");
}
