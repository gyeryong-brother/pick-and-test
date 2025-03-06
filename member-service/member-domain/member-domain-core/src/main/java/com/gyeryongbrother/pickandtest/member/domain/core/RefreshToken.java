package com.gyeryongbrother.pickandtest.member.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class RefreshToken {

    private final Long id;
    private final String username;
    private final String refreshToken;
}
