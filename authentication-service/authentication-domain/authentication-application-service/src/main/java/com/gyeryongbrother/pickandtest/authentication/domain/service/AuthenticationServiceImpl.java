package com.gyeryongbrother.pickandtest.authentication.domain.service;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.INVALID_REFRESH_TOKEN;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USER_NONEXISTS;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.RegisteredCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;
    private final RefreshTokenQueryRepository refreshTokenQueryRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final Authenticator authenticator;

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
    public void logout(String refreshToken) {
        refreshTokenQueryRepository.findByToken(refreshToken)
                .orElseThrow(() -> new AuthenticationServiceException(INVALID_REFRESH_TOKEN));
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
