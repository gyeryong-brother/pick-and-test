package com.gyeryongbrother.pickandtest.noticeboard.application.exception.handler;

import static com.gyeryongbrother.pickandtest.noticeboard.application.exception.handler.dto.ErrorResponse.INTERNAL_SERVER_ERROR_RESPONSE;

import com.gyeryongbrother.pickandtest.noticeboard.application.exception.handler.dto.ErrorResponse;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class NoticeboardExceptionHandler {


    private ResponseEntity<ErrorResponse> getResponse(BaseExceptionType exceptionTypen){
        return ResponseEntity.status(exceptionTypen.httpStatus())
                .body(new ErrorResponse(exceptionTypen.errorMessage()));
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
