package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

public class Member {

    private final Long id;
    private final String nickname;
    private final String profileImageUrl;

    public Member(Long id, String nickname, String profileImageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public Member(String nickname, String profileImageUrl) {
        this(null, nickname, profileImageUrl);
    }

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
