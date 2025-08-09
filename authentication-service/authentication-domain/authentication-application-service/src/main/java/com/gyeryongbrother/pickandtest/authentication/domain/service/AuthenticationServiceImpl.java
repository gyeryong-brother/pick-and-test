package com.gyeryongbrother.pickandtest.authentication.domain.service;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.REFRESH_TOKEN_NOT_REGISTERED;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USERNAME_ALREADY_EXISTS;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.PasswordEncryptor;
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
    private final PasswordEncryptor passwordEncryptor;

    @Override
    public RegisterResponse register(RegisterCommand command) {
        validateAlreadyExists(command.username());
        Member member = memberClient.registerMember(command.toDomain());
        String encryptedPassword = passwordEncryptor.encrypt(command.password());
        UsernamePasswordCredential credential = command.toCredential(member.id(), encryptedPassword);
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
    public void logout(String refreshToken) {
        refreshTokenQueryRepository.findByToken(refreshToken)
                .orElseThrow(() -> new AuthenticationServiceException(REFRESH_TOKEN_NOT_REGISTERED));
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
