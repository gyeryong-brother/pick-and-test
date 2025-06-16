package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType.KAKAO_PROFILE;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType.KAKAO_TOKEN;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestContext;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile.dto.KakaoProfileResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token.dto.KakaoTokenResponse;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoClient implements OauthClient {

    private final FetcherComposite fetcherComposite;

    @Override
    public AuthenticationMethod support() {
        return AuthenticationMethod.KAKAO;
    }

    @Override
    public OauthMember fetchMember(String authorizationCode) {
        RequestContext tokenContext = tokenContext(authorizationCode);
        KakaoTokenResponse kakaoTokenResponse = fetcherComposite.fetch(
                KAKAO_TOKEN,
                tokenContext,
                KakaoTokenResponse.class
        );
        String accessToken = kakaoTokenResponse.accessToken();
        RequestContext profileContext = profileContext(accessToken);
        KakaoProfileResponse kakaoProfileResponse = fetcherComposite.fetch(
                KAKAO_PROFILE,
                profileContext,
                KakaoProfileResponse.class
        );
        return kakaoProfileResponse.toDomain();
    }

    private RequestContext tokenContext(String authorizationCode) {
        RequestContext context = new RequestContext();
        context.put("authorizationCode", authorizationCode);
        return context;
    }

    private RequestContext profileContext(String token) {
        RequestContext context = new RequestContext();
        context.put("token", token);
        return context;
    }
}
