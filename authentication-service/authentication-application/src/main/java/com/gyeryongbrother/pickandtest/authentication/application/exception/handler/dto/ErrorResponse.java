package com.gyeryongbrother.pickandtest.authentication.application.exception.handler.dto;

public record ErrorResponse(
        String errorMessage
) {

    public static ErrorResponse INTERNAL_SERVER_ERROR_RESPONSE = new ErrorResponse("알 수 없는 오류가 발생했습니다.");

    public static ErrorResponse MISSING_COOKIE_ERROR_RESPONSE = new ErrorResponse("리프레시 토큰이 필요합니다.");
}
