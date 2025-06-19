package com.gyeryongbrother.pickandtest.authentication.domain.service;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.INVALID_REFRESH_TOKEN;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USERNAME_ALREADY_EXISTS;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.UsernamePasswordLoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
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
    private final MemberClient memberClient;

    @Override
    public RegisterResponse register(RegisterCommand command) {
        validateAlreadyExists(command.username());
        Member member = memberClient.registerMember(command.toDomain());
        UsernamePasswordCredential credential = command.toCredential(member.id());
        UsernamePasswordCredential savedCredential = usernamePasswordCredentialRepository.save(credential);
        return new RegisterResponse(savedCredential.memberId(), savedCredential.username());
    }

    private void validateAlreadyExists(String username) {
        Optional<UsernamePasswordCredential> usernamePasswordCredential =
                usernamePasswordCredentialQueryRepository.findByUsername(username);
        if (usernamePasswordCredential.isPresent()) {
            throw new AuthenticationServiceException(USERNAME_ALREADY_EXISTS);
        }
    }

    @Override
    public LoginResponse login(UsernamePasswordLoginCommand command) {
//        Tokens tokens = authenticator.authenticate(command.toAuthenticationAttempt());
        Tokens tokens = new Tokens(1L, "asdf", "asdf");
        RefreshToken refreshToken = new RefreshToken(tokens.memberId(), tokens.refreshToken());
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
