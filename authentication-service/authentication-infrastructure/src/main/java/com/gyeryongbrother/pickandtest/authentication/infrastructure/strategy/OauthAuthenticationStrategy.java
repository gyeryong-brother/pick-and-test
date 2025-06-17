package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OauthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClientComposite;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthMember;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthAuthenticationStrategy implements AuthenticationStrategy {

    private final OauthClientComposite oauthClientComposite;
    private final MemberClient memberClient;
    private final JwtProvider jwtProvider;
    private final OauthCredentialQueryRepository oauthCredentialQueryRepository;
    private final OauthCredentialRepository oauthCredentialRepository;

    @Override
    public Set<AuthenticationMethod> support() {
        return Set.of(AuthenticationMethod.KAKAO, AuthenticationMethod.NAVER);
    }

    @Override
    public Tokens authenticate(AuthenticationAttempt attempt) {
        String authorizationCode = attempt.credentials();
        OauthClient oauthClient = oauthClientComposite.oauthClient(attempt.method());
        OauthMember oauthMember = oauthClient.fetchMember(authorizationCode);
        OauthCredential oauthCredential = oauthCredentialQueryRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(() -> registerMember(oauthMember));
        return jwtProvider.createTokens(oauthCredential.memberId(), oauthCredential.memberRole());
    }

    private OauthCredential registerMember(OauthMember oauthMember) {
        Member member = memberClient.registerMember(oauthMember.toDomain());
        OauthId oauthId = oauthMember.oauthId();
        OauthCredential oauthCredential = new OauthCredential(member.id(), MemberRole.USER, oauthId);
        return oauthCredentialRepository.save(oauthCredential);
    }
}
