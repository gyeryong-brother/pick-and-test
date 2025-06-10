package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MemberServiceUrlProvider {

    private final String url;

    public MemberServiceUrlProvider(
            @Value("${member-service.url}") String url
    ) {
        this.url = url;
    }

    public String getRegisterMemberUrl() {
        return UriComponentsBuilder.fromHttpUrl(url)
                .path("/member-service/members")
                .toUriString();
    }
}
