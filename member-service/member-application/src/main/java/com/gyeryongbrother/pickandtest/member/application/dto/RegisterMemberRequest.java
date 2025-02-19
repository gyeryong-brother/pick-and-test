package com.gyeryongbrother.pickandtest.member.application.dto;

import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;

public record RegisterMemberRequest(
        String name,
        String userId,
        String password
) {

    public RegisterMemberCommand toCommand() {
        return new RegisterMemberCommand(name, userId, password);
    }
}
