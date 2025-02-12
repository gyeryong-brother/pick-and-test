package com.gyeryongbrother.pickandtest.member.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Member {

    private final Long id;
    private final String name;
    private String userId;
    private String password;
    private String refreshToken;
    private UserRole userRole;
}
