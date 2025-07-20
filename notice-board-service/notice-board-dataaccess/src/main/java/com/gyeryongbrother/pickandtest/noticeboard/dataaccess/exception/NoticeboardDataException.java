package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.exception;

import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoticeboardDataException extends BaseException {

    private final NoticeboardDataExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
