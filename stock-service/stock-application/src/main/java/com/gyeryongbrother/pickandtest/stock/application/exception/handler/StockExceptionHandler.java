package com.gyeryongbrother.pickandtest.stock.application.exception.handler;

import static com.gyeryongbrother.pickandtest.stock.application.exception.handler.dto.ErrorResponse.INTERNAL_SERVER_ERROR_RESPONSE;

import com.gyeryongbrother.pickandtest.stock.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.exception.CoreException;
import com.gyeryongbrother.pickandtest.stock.domain.core.exception.CoreExceptionType;
import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.stock.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.stock.domain.service.exception.CoreExceptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class StockExceptionHandler {

    private final CoreExceptionMapper coreExceptionMapper;

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(CoreException exception) {
        CoreExceptionType exceptionType = exception.exceptionType();
        return getResponse(coreExceptionMapper.map(exceptionType));
    }

    private ResponseEntity<ErrorResponse> getResponse(BaseExceptionType exceptionType) {
        return ResponseEntity.status(exceptionType.httpStatus())
                .body(new ErrorResponse(exceptionType.errorMessage()));
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(BaseException exception) {
        return getResponse(exception.exceptionType());
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError()
                .body(INTERNAL_SERVER_ERROR_RESPONSE);
    }
}
