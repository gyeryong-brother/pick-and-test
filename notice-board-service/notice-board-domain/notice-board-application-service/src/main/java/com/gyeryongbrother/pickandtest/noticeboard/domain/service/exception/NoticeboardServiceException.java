package com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoticeboardServiceException extends BaseException{

    private final NoticeboardServiceExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
