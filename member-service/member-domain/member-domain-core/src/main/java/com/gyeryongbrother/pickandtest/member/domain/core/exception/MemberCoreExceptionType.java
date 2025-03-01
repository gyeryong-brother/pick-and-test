package com.gyeryongbrother.pickandtest.member.domain.core.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberCoreExceptionType implements CoreExceptionType {

    INVALID_USERROLE("잘못된 사용자 정보입니다."),
    ;

    private final String errorMessage;

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
