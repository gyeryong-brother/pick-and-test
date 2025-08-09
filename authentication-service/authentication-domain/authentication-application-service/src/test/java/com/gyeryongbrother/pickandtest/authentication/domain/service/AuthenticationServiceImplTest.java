package com.gyeryongbrother.pickandtest.authentication.domain.service;

import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.PasswordEncryptor;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("인증 서비스를 구현한다")
class AuthenticationServiceImplTest {

    @Mock
    private UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;

    @Mock
    private UsernamePasswordCredentialRepository usernamePasswordCredentialRepository;

    @Mock
    private RefreshTokenQueryRepository refreshTokenQueryRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private MemberClient memberClient;

    @Mock
    private PasswordEncryptor passwordEncryptor;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationServiceImpl(
                usernamePasswordCredentialQueryRepository,
                usernamePasswordCredentialRepository,
                refreshTokenQueryRepository,
                refreshTokenRepository,
                memberClient,
                passwordEncryptor
        );
    }
}
