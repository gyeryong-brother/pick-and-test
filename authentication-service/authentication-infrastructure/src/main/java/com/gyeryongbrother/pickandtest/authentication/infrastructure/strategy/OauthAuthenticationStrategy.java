package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthClientComposite;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.OauthId;
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
//    private final OauthCredentialQueryRepository oauthCredentialQueryRepository;
//    private final OauthCredentialRepository oauthCredentialRepository;

    @Override
    public Set<AuthenticationMethod> supports() {
        return Set.of(AuthenticationMethod.KAKAO, AuthenticationMethod.NAVER);
    }

    @Override
    public Tokens authenticate(AuthenticationAttempt authenticationAttempt) {
        String authorizationCode = authenticationAttempt.credentials();
        OauthClient oauthClient = oauthClientComposite.oauthClient(authenticationAttempt.method());
        OauthMember oauthMember = oauthClient.fetchMember(authorizationCode);
//        OauthCredential oauthCredential = oauthCredentialQueryRepository.findByOauthId(oauthMember.oauthId().toString())
//                .orElseGet(() -> registerMember(oauthMember));
//        return createToken(oauthCredential);
        return null;
    }

    private OauthCredential registerMember(OauthMember oauthMember) {
        Member member = memberClient.registerMember(oauthMember.toDomain());
        OauthId oauthId = oauthMember.oauthId();
        OauthCredential oauthCredential = new OauthCredential(null, member.id(), oauthId.toString(),
                oauthId.authenticationMethod());
//        return oauthCredentialRepository.save(oauthCredential);
        return null;
    }

    private Tokens createToken(OauthCredential oauthCredential) {
        return tokens(oauthCredential.principal(), MemberRole.USER);
    }

    private Tokens tokens(Long memberId, MemberRole memberRole) {
        String accessToken = jwtProvider.generateAccessToken(memberId, memberRole);
        String refreshToken = jwtProvider.generateRefreshToken(memberId, memberRole);
        return new Tokens(accessToken, refreshToken);
    }
}
