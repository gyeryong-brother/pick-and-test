package com.gyeryongbrother.pickandtest.member.infrastructure.exception;

import com.gyeryongbrother.pickandtest.member.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.BaseExceptionType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberInfrastructureException extends BaseException {

    private final MemberInfrastructureExceptionType exceptionType;

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
