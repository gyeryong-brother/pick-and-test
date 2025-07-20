package com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    HttpStatus httpStatus();

    String errorMessage();
}
