package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao.dto.KakaoProfileResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao.dto.KakaoTokenResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoClient implements OauthClient {

    private final KakaoApiFetcher kakaoApiFetcher;

    @Override
    public AuthenticationMethod support() {
        return AuthenticationMethod.KAKAO;
    }

    @Override
    public OauthMember fetchMember(String authorizationCode) {
        KakaoTokenResponse kakaoTokenResponse = kakaoApiFetcher.fetchToken(authorizationCode);
        String accessToken = kakaoTokenResponse.accessToken();
        KakaoProfileResponse kakaoProfileResponse = kakaoApiFetcher.fetchProfile(accessToken);
        return kakaoProfileResponse.toDomain();
    }
}
