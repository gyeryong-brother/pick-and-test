package com.gyeryongbrother.pickandtest.authentication.domain.service;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.INVALID_REFRESH_TOKEN;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USERNAME_ALREADY_EXISTS;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USER_NONEXISTS;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.RegisteredCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthorizationCodeAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.UsernamePasswordAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.OauthLoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.RegisterCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;
    private final UsernamePasswordCredentialRepository usernamePasswordCredentialRepository;
    private final RefreshTokenQueryRepository refreshTokenQueryRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final Authenticator authenticator;
    private final MemberClient memberClient;

    @Override
    public LoginResponse register(RegisterCommand command) {
        validateAlreadyExists(command.username());
        Member member = memberClient.registerMember(command.toDomain());
        UsernamePasswordCredential credential = command.toCredential(member.id());
        UsernamePasswordCredential savedCredential = usernamePasswordCredentialRepository.save(credential);
        AuthenticationContext context = new AuthenticationContext(new UsernamePasswordAttempt(command.username(), command.password()), savedCredential);
        Tokens tokens = authenticator.authenticate(context);
        RefreshToken refreshToken = new RefreshToken(credential.principal(), tokens.refreshToken());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        return new LoginResponse(tokens.accessToken(), savedRefreshToken.token());
    }

    private void validateAlreadyExists(String username) {
        Optional<UsernamePasswordCredential> usernamePasswordCredential =
                usernamePasswordCredentialQueryRepository.findByUsername(username);
        if (usernamePasswordCredential.isPresent()) {
            throw new AuthenticationServiceException(USERNAME_ALREADY_EXISTS);
        }
    }

    @Override
    public LoginResponse login(LoginCommand loginCommand) {
        String username = loginCommand.username();
        RegisteredCredential credential = usernamePasswordCredentialQueryRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationServiceException(USER_NONEXISTS));
        AuthenticationContext context = new AuthenticationContext(loginCommand.toDomain(), credential);
        Tokens tokens = authenticator.authenticate(context);
        RefreshToken refreshToken = new RefreshToken(credential.principal(), tokens.refreshToken());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        return new LoginResponse(tokens.accessToken(), savedRefreshToken.token());
    }

    @Override
    public LoginResponse oauthLogin(OauthLoginCommand command) {
        AuthenticationContext context = new AuthenticationContext(
                new AuthorizationCodeAttempt(command.code(), command.authenticationMethod()),
                new OauthCredential(null, null, null, command.authenticationMethod())
        );
        Tokens tokens = authenticator.authenticate(context);
        RefreshToken refreshToken = new RefreshToken(null, tokens.refreshToken());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
        return new LoginResponse(tokens.accessToken(), savedRefreshToken.token());
    }

    @Override
    public void logout(String refreshToken) {
        refreshTokenQueryRepository.findByToken(refreshToken)
                .orElseThrow(() -> new AuthenticationServiceException(INVALID_REFRESH_TOKEN));
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
