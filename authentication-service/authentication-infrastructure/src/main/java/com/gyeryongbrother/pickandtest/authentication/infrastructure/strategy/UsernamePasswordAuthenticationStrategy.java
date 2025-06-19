package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USER_NONEXISTS;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.USERNAME_PASSWORD_NOT_MATCH;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationStrategy {

    private final UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Tokens authenticate(String username, String password) {
        UsernamePasswordCredential credential = usernamePasswordCredentialQueryRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationServiceException(USER_NONEXISTS));
        if (passwordEncoder.matches(password, credential.password())) {
            return jwtProvider.createTokens(credential.memberId(), credential.memberRole());
        }
        throw new AuthenticationInfrastructureException(USERNAME_PASSWORD_NOT_MATCH);
    }
}
