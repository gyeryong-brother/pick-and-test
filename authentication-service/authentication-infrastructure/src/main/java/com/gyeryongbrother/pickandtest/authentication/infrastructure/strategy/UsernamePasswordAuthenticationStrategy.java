package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USER_NONEXISTS;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.USERNAME_PASSWORD_NOT_MATCH;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationStrategy implements AuthenticationStrategy {

//    private final UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public Set<AuthenticationMethod> supports() {
        return Set.of(AuthenticationMethod.GYERYONG_BROTHER);
    }

    @Override
    public Tokens authenticate(AuthenticationAttempt authenticationAttempt) {
        String username = authenticationAttempt.principal();
//        UsernamePasswordCredential usernamePasswordCredential = usernamePasswordCredentialQueryRepository.findByUsername(
//                        username)
//                .orElseThrow(() -> new AuthenticationServiceException(USER_NONEXISTS));
//        if (passwordEncoder.matches(authenticationAttempt.credentials(), usernamePasswordCredential.password())) {
//            return tokens(usernamePasswordCredential.memberId(), MemberRole.USER);
//        }
        throw new AuthenticationInfrastructureException(USERNAME_PASSWORD_NOT_MATCH);
    }

    private Tokens tokens(Long memberId, MemberRole memberRole) {
        String accessToken = jwtProvider.generateAccessToken(memberId, memberRole);
        String refreshToken = jwtProvider.generateRefreshToken(memberId, memberRole);
        return new Tokens(accessToken, refreshToken);
    }
}
