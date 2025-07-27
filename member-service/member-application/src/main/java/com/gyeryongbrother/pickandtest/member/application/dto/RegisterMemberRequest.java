package com.gyeryongbrother.pickandtest.member.application.dto;

import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;

public record RegisterMemberRequest(
        String nickname,
        String profileImageUrl
) {

    public RegisterMemberCommand toCommand() {
        return new RegisterMemberCommand(nickname, profileImageUrl);
    }
}
