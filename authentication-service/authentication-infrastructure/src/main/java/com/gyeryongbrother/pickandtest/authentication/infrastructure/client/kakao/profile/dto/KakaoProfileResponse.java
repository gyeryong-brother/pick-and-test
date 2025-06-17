package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OauthId;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;

@JsonNaming(SnakeCaseStrategy.class)
public record KakaoProfileResponse(
        Long id,
        KakaoAccount kakaoAccount
) {

    public OauthMember toDomain() {
        return new OauthMember(
                new OauthId(AuthenticationMethod.KAKAO, String.valueOf(id)),
                kakaoAccount.profile().nickname(),
                kakaoAccount.profile().profileImageUrl()
        );
    }
}
