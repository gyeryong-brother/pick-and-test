package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MemberServiceApiFetcher {

    private final RestTemplate restTemplate;
    private final MemberServiceApiUrlProvider memberServiceApiUrlProvider;

    public RegisterMemberResponse registerMember(RegisterMemberRequest request) {
        String url = memberServiceApiUrlProvider.getRegisterMemberUrl();
        return restTemplate.postForEntity(url, request, RegisterMemberResponse.class)
                .getBody();
    }
}
