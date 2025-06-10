package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthId;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthServerType;

@JsonNaming(SnakeCaseStrategy.class)
public record KakaoProfileResponse(
        Long id,
        KakaoAccount kakaoAccount
) {

    public OauthMember toDomain() {
        return new OauthMember(
                new OauthId(String.valueOf(id), OauthServerType.KAKAO),
                kakaoAccount.profile().nickname(),
                kakaoAccount.profile().profileImageUrl()
        );
    }
}
