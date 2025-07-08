package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input.OAuthCredentialService;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter.OAuthUserProfileConverterProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter.TestOAuthUserProfileConverter;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

@ExtendWith(MockitoExtension.class)
class CustomOAuthUserServiceTest {

    @Mock
    private DefaultOAuth2UserService defaultOAuth2UserService;

    @Mock
    private OAuthCredentialService oAuthCredentialService;

    private CustomOAuthUserService customOAuthUserService;

    @BeforeEach
    void setUp() {
        OAuthUserProfileConverterProvider profileConverterProvider = new OAuthUserProfileConverterProvider(Set.of(
                new TestOAuthUserProfileConverter()
        ));
        customOAuthUserService = new CustomOAuthUserService(
                defaultOAuth2UserService,
                profileConverterProvider,
                oAuthCredentialService
        );
    }

    @Test
    @DisplayName("회원을 불러온다")
    void loadUser() {
        // given
        OAuth2UserRequest request = mock(OAuth2UserRequest.class);
        OAuth2User oAuth2User = mock(OAuth2User.class);
        ClientRegistration clientRegistration = mock(ClientRegistration.class);
        given(request.getClientRegistration())
                .willReturn(clientRegistration);
        given(clientRegistration.getRegistrationId())
                .willReturn("test_supported");
        given(defaultOAuth2UserService.loadUser(any()))
                .willReturn(oAuth2User);
        OAuthCredential oAuthCredential = new OAuthCredential(
                1L,
                MemberRole.USER,
                new OAuthId(OAuthType.TEST_SUPPORTED, "oAuthId")
        );
        given(oAuthCredentialService.getOrRegisterOAuthCredential(any()))
                .willReturn(oAuthCredential);
        CustomOAuth2User expected = new CustomOAuth2User(oAuthCredential, oAuth2User);

        // when
        OAuth2User result = customOAuthUserService.loadUser(request);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
