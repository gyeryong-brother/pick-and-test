package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.MemberApi;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberClientImpl implements MemberClient {

    private final MemberApi memberApi;

    @Override
    public Member registerMember(Member member) {
        RegisterMemberRequest request = new RegisterMemberRequest(member.nickname(), member.profileImageUrl());
        RegisterMemberResponse response = memberApi.registerMember(request);
        return response.toDomain();
    }
}
