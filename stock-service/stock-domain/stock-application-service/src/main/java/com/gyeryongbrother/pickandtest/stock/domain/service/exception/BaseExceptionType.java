package com.gyeryongbrother.pickandtest.stock.domain.service.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    HttpStatus httpStatus();

    String errorMessage();
}
