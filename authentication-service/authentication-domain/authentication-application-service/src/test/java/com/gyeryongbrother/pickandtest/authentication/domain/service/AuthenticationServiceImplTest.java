package com.gyeryongbrother.pickandtest.authentication.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.AuthenticationService;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
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
    private RefreshTokenQueryRepository refreshTokenQueryRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private Authenticator authenticator;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationServiceImpl(
                usernamePasswordCredentialQueryRepository,
                refreshTokenQueryRepository,
                refreshTokenRepository,
                authenticator
        );
    }

    @Test
    @DisplayName("로그인을 한다")
    void login() {
        //given
        given(usernamePasswordCredentialQueryRepository.findByUsername(any()))
                .willReturn(Optional.of(new UsernamePasswordCredential(1L, 1L, "username", "password")));
        given(authenticator.authenticate(any()))
                .willReturn(new Tokens("accessToken", "refreshToken"));
        given(refreshTokenRepository.save(any()))
                .willReturn(new RefreshToken(1L, 1L, "refreshToken"));
        LoginCommand loginCommand = new LoginCommand("username", "password");
        LoginResponse expected = new LoginResponse("accessToken", "refreshToken");

        //when
        LoginResponse result = authenticationService.login(loginCommand);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
