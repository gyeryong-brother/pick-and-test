package com.gyeryongbrother.pickandtest.authentication.application.exception.handler;

import static com.gyeryongbrother.pickandtest.authentication.application.exception.handler.dto.ErrorResponse.INTERNAL_SERVER_ERROR_RESPONSE;
import static com.gyeryongbrother.pickandtest.authentication.application.exception.handler.dto.ErrorResponse.MISSING_COOKIE_ERROR_RESPONSE;

import com.gyeryongbrother.pickandtest.authentication.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class AuthenticationExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(BaseException exception) {
        BaseExceptionType exceptionType = exception.exceptionType();
        return ResponseEntity.status(exceptionType.httpStatus())
                .body(new ErrorResponse(exceptionType.errorMessage()));
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(MissingRequestCookieException exception) {
        return ResponseEntity.badRequest()
                .body(MISSING_COOKIE_ERROR_RESPONSE);
    }

    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("ERROR MESSAGE: {}", exception.getMessage());
        return ResponseEntity.internalServerError()
                .body(INTERNAL_SERVER_ERROR_RESPONSE);
    }
}
