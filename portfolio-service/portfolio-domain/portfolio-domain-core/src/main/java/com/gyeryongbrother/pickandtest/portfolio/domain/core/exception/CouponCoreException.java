package com.gyeryongbrother.pickandtest.portfolio.domain.core.exception;

public class CouponCoreException extends RuntimeException {

    public CouponCoreException(String message) {
        super(message);
    }

    public CouponCoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
