package com.gyeryongbrother.pickandtest.portfolio.domain.service.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    HttpStatus getHttpStatus();

    String getErrorMessage();
}
