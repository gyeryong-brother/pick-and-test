package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.USERNAME_PASSWORD_NOT_MATCH;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.RegisteredCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationStrategy implements AuthenticationStrategy {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthenticationMethod method() {
        return AuthenticationMethod.GYERYONG_BROTHER;
    }

    @Override
    public Tokens authenticate(AuthenticationContext authenticationContext) {
        AuthenticationAttempt attempt = authenticationContext.authenticationAttempt();
        RegisteredCredential credential = authenticationContext.registeredCredential();
        if (credential.matches(it -> passwordEncoder.matches(attempt.secret(), it))) {
            return tokens(credential.principal(), MemberRole.USER);
        }
        throw new AuthenticationInfrastructureException(USERNAME_PASSWORD_NOT_MATCH);
    }

    private Tokens tokens(Long memberId, MemberRole memberRole) {
        String accessToken = jwtProvider.generateAccessToken(memberId, memberRole);
        String refreshToken = jwtProvider.generateRefreshToken(memberId, memberRole);
        return new Tokens(accessToken, refreshToken);
    }
}
