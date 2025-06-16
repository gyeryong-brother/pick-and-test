package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType.KAKAO_TOKEN;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.FormRequest;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Request;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestContext;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao.KakaoOauthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenRequestProvider implements RequestProvider {

    private static final String AUTHORIZATION_CODE = "authorizationCode";

    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public RequestType support() {
        return KAKAO_TOKEN;
    }

    @Override
    public Request provide(RequestContext requestContext) {
        String authorizationCode = requestContext.get(AUTHORIZATION_CODE);
        TokenRequestBody tokenRequestBody = new TokenRequestBody(
                kakaoOauthConfig.clientId(),
                kakaoOauthConfig.redirectUri(),
                authorizationCode,
                kakaoOauthConfig.clientSecret()
        );
        return new FormRequest(tokenRequestBody);
    }
}
