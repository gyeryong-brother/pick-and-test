package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.ProfileFetcher;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.dto.KakaoProfileResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token.TokenFetcher;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token.dto.KakaoTokenResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoClient {

    private final TokenFetcher tokenFetcher;
    private final ProfileFetcher profileFetcher;

    public OauthMember fetchMember(String authorizationCode) {
        KakaoTokenResponse kakaoTokenResponse = tokenFetcher.fetchToken(authorizationCode);
        KakaoProfileResponse kakaoProfileResponse = profileFetcher.fetchProfile(kakaoTokenResponse.accessToken());
        return kakaoProfileResponse.toDomain();
    }
}
