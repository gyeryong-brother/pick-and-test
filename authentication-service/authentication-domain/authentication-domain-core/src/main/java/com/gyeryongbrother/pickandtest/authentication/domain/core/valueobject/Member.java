package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Member {

    private final Long id;
    private final String nickname;
    private final String profileImageUrl;

    public Long id() {
        return id;
    }

    public String nickname() {
        return nickname;
    }

    public String profileImageUrl() {
        return profileImageUrl;
    }
}
