package com.gyeryongbrother.pickandtest.member.domain.service.dto;

public class RegisterMemberCommandFixture {

    public static RegisterMemberCommand registerMemberCommand() {
        return new RegisterMemberCommand("nickname", "profileImageUrl");
    }
}
