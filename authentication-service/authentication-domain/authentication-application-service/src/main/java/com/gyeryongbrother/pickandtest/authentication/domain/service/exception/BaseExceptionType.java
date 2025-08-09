package com.gyeryongbrother.pickandtest.authentication.domain.service.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    HttpStatus httpStatus();

    String errorMessage();
}
