package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import static java.util.Locale.ENGLISH;

public enum AuthenticationMethod {

    GYERYONG_BROTHER,
    NAVER,
    KAKAO,
    ;

    public static AuthenticationMethod from(String value) {
        return AuthenticationMethod.valueOf(value.toUpperCase(ENGLISH));
    }
}
