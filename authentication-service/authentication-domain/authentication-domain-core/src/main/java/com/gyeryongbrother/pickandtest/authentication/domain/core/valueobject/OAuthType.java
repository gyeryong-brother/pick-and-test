package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import static java.util.Locale.ENGLISH;

public enum OAuthType {

    NAVER,
    KAKAO,
    ;

    public static OAuthType from(String value) {
        return OAuthType.valueOf(value.toUpperCase(ENGLISH));
    }
}
