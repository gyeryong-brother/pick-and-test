package com.gyeryongbrother.pickandtest.member.domain.service.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceException extends BaseException {

    private final MemberServiceExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
