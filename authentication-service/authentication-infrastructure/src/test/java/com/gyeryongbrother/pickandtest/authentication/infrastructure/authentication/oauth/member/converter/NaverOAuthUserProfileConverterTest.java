package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthMember;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.OAuth2User;

@ExtendWith(MockitoExtension.class)
class NaverOAuthUserProfileConverterTest {

    @Mock
    private OAuth2User oAuth2User;

    private NaverOAuthUserProfileConverter naverOAuthMemberConverter;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        naverOAuthMemberConverter = new NaverOAuthUserProfileConverter(objectMapper);
    }

    @Test
    @DisplayName("네이버 타입을 지원한다")
    void support() {
        // when
        OAuthType result = naverOAuthMemberConverter.getSupportedOAuthType();

        // then
        assertThat(result).isEqualTo(OAuthType.NAVER);
    }

    @Test
    @DisplayName("네이버 프로필 응답을 OAuthMember 로 변환한다")
    void convert() {
        // given
        Map<String, Object> naverMemberInfo = Map.of(
                "id", "naverOAuthId",
                "name", "odo27",
                "profile_image", "profileImageUrl"
        );
        Map<String, Object> naverAttributes = Map.of(
                "response", naverMemberInfo
        );
        given(oAuth2User.getAttributes())
                .willReturn(naverAttributes);
        OAuthMember expected = new OAuthMember(
                new OAuthId(OAuthType.NAVER, "naverOAuthId"),
                "odo27",
                "profileImageUrl"
        );

        // when
        OAuthMember result = naverOAuthMemberConverter.convert(oAuth2User);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
