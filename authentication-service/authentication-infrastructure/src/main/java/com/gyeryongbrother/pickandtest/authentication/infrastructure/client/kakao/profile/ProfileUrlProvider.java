package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.profile;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType.KAKAO_PROFILE;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Url;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.UrlProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ProfileUrlProvider implements UrlProvider {

    private static final String DOMAIN = "https://kapi.kakao.com";

    @Override
    public RequestType support() {
        return KAKAO_PROFILE;
    }

    @Override
    public Url provide() {
        String url = UriComponentsBuilder.fromHttpUrl(DOMAIN)
                .path("/v2")
                .path("/user")
                .path("/me")
                .toUriString();
        return new Url(url);
    }
}
