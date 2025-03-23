package com.gyeryongbrother.pickandtest.stockprice.domain.service.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

    HttpStatus httpStatus();

    String errorMessage();
}
