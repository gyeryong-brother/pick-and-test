package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType.KAKAO_TOKEN;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Url;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.UrlProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TokenUrlProvider implements UrlProvider {

    private static final String DOMAIN = "https://kauth.kakao.com";

    @Override
    public RequestType support() {
        return KAKAO_TOKEN;
    }

    @Override
    public Url provide() {
        String url = UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path("/oauth")
                .path("/token")
                .toUriString();
        return new Url(url);
    }
}
