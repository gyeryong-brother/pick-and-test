package com.gyeryongbrother.pickandtest.authentication.domain.service;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthMember;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.OAuthCredentialService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OAuthCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OAuthCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthCredentialServiceImpl implements OAuthCredentialService {

    private final MemberClient memberClient;
    private final OAuthCredentialRepository oauthCredentialRepository;
    private final OAuthCredentialQueryRepository oauthCredentialQueryRepository;

    @Override
    public OAuthCredential getOrRegisterOAuthCredential(OAuthMember oAuthMember) {
        return oauthCredentialQueryRepository.findByOauthId(oAuthMember.oAuthId())
                .orElseGet(() -> registerOAuthMember(oAuthMember));
    }

    private OAuthCredential registerOAuthMember(OAuthMember oAuthMember) {
        Member member = memberClient.registerMember(oAuthMember.toMember());
        OAuthCredential oauthCredential = new OAuthCredential(member.id(), MemberRole.USER, oAuthMember.oAuthId());
        return oauthCredentialRepository.save(oauthCredential);
    }
}
