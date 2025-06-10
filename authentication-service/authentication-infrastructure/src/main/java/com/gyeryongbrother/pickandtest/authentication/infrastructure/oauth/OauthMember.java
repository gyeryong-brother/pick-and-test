package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthMember {

    private final OauthId oauthId;
    private final String nickname;
    private final String profileImageUrl;
}
