package com.gyeryongbrother.pickandtest.member.domain.core;

public class Member {

    private final Long id;
    private final MemberRole memberRole;
    private final String nickname;
    private final String profileImageUrl;

    public Member(Long id, MemberRole memberRole, String nickname, String profileImageUrl) {
        this.id = id;
        this.memberRole = memberRole;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public Member(String nickname, String profileImageUrl) {
        this(null, MemberRole.ROLE_USER, nickname, profileImageUrl);
    }

    public Long id() {
        return id;
    }

    public MemberRole memberRole() {
        return memberRole;
    }

    public String nickname() {
        return nickname;
    }

    public String profileImageUrl() {
        return profileImageUrl;
    }
}
