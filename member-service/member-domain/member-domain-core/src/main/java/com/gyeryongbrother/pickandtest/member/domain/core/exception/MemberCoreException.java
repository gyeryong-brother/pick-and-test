package com.gyeryongbrother.pickandtest.member.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberCoreException extends CoreException {

    private final CoreExceptionType exceptionType;

    @Override
    public CoreExceptionType exceptionType() {
        return exceptionType;
    }
}
