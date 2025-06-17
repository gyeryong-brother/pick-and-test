package com.gyeryongbrother.pickandtest.authentication.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.UsernamePasswordLoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    private Authenticator authenticator;

    @Mock
    private MemberClient memberClient;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationServiceImpl(
                usernamePasswordCredentialQueryRepository,
                usernamePasswordCredentialRepository,
                refreshTokenQueryRepository,
                refreshTokenRepository,
                authenticator,
                memberClient
        );
    }

    @Test
    @DisplayName("로그인을 한다")
    void login() {
        //given
        given(usernamePasswordCredentialQueryRepository.findByUsername(any()))
                .willReturn(Optional.of(new UsernamePasswordCredential(1L, 1L, MemberRole.USER, "username", "password")));
        given(authenticator.authenticate(any()))
                .willReturn(new Tokens(1L, "accessToken", "refreshToken"));
        given(refreshTokenRepository.save(any()))
                .willReturn(new RefreshToken(1L, 1L, "refreshToken"));
        UsernamePasswordLoginCommand usernamePasswordLoginCommand = new UsernamePasswordLoginCommand("username", "password");
        LoginResponse expected = new LoginResponse("accessToken", "refreshToken");

        //when
        LoginResponse result = authenticationService.login(usernamePasswordLoginCommand);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
