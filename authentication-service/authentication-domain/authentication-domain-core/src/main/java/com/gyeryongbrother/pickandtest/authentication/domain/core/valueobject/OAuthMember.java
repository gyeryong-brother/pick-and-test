package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

public class OAuthMember {

    private final OAuthId oAuthId;
    private final String nickname;
    private final String profileImageUrl;

    public OAuthMember(OAuthId oAuthId, String nickname, String profileImageUrl) {
        this.oAuthId = oAuthId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public Member toMember() {
        return new Member(nickname, profileImageUrl);
    }

    public OAuthId oAuthId() {
        return oAuthId;
    }
}
