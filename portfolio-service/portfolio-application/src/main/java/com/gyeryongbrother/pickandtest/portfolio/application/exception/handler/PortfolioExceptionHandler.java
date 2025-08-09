package com.gyeryongbrother.pickandtest.portfolio.application.exception.handler;

import static com.gyeryongbrother.pickandtest.portfolio.application.exception.handler.dto.ErrorResponse.INTERNAL_SERVER_ERROR_RESPONSE;

import com.gyeryongbrother.pickandtest.portfolio.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.BaseExceptionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PortfolioExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(BaseException exception) {
        BaseExceptionType exceptionType = exception.exceptionType();
        return ResponseEntity.status(exceptionType.httpStatus())
                .body(new ErrorResponse(exceptionType.errorMessage()));
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return ResponseEntity.internalServerError()
                .body(INTERNAL_SERVER_ERROR_RESPONSE);
    }
}
