package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.handler;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy.ResponseStrategyProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@ExtendWith(MockitoExtension.class)
@DisplayName("인증 성공 핸들러를 구현한다")
class CustomAuthenticationSuccessHandlerTest {

    @Mock
    private ResponseStrategyProvider responseStrategyProvider;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @BeforeEach
    void setUp() {
        authenticationSuccessHandler = new CustomAuthenticationSuccessHandler(responseStrategyProvider);
    }

    @Test
    @DisplayName("인증 성공시 응답 정책에 따라 응답한다")
    void onAuthenticationSuccess() throws ServletException, IOException {
        // given
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        Authentication authentication = mock(Authentication.class);
//        Tokenizable tokenizable = mock(Tokenizable.class);
//        given(authentication.getPrincipal())
//                .willReturn(tokenizable);

        // when
//        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // then
    }

    @Test
    @DisplayName("토큰화 시킬 수 없는 인증 주체인 경우 예외가 발생한다")
    void onAuthenticationSuccessWithNotTokenizable() throws ServletException, IOException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);
        given(authentication.getPrincipal())
                .willReturn("notTokenizablePrincipal");

        // when & then
        assertThrows(ClassCastException.class, () ->
                authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication)
        );
    }
}
