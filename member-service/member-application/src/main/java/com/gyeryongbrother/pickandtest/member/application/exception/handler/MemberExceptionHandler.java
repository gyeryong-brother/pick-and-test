package com.gyeryongbrother.pickandtest.member.application.exception.handler;

import static com.gyeryongbrother.pickandtest.member.application.exception.handler.dto.ErrorResponse.INTERNAL_SERVER_ERROR_RESPONSE;

import com.gyeryongbrother.pickandtest.member.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.member.domain.core.exception.CoreException;
import com.gyeryongbrother.pickandtest.member.domain.core.exception.CoreExceptionType;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.CoreExceptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class MemberExceptionHandler {

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
