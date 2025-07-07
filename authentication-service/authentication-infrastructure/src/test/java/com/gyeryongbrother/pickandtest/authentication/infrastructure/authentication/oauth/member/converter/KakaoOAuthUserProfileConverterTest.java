package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member.OAuthMember;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.OAuth2User;

@ExtendWith(MockitoExtension.class)
class KakaoOAuthUserProfileConverterTest {

    @Mock
    private OAuth2User oAuth2User;

    private KakaoOAuthUserProfileConverter kakaoOAuthMemberConverter;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        kakaoOAuthMemberConverter = new KakaoOAuthUserProfileConverter(objectMapper);
    }

    @Test
    @DisplayName("카카오 타입을 지원한다")
    void support() {
        // when
        OAuthType result = kakaoOAuthMemberConverter.getSupportedOAuthType();

        // then
        assertThat(result).isEqualTo(OAuthType.KAKAO);
    }

    @Test
    @DisplayName("카카오 프로필 응답을 OAuthMember 로 변환한다")
    void convert() {
        // given
        Map<String, Object> kakaoMemberInfo = Map.of(
                "nickname", "odo27",
                "profile_image", "profileImageUrl"
        );
        Map<String, Object> kakaoAttributes = Map.of(
                "id", "kakaoOAuthId",
                "properties", kakaoMemberInfo
        );
        given(oAuth2User.getAttributes())
                .willReturn(kakaoAttributes);
        OAuthMember expected = new OAuthMember(
                new OAuthId(OAuthType.KAKAO, "kakaoOAuthId"),
                "odo27",
                "profileImageUrl"
        );

        // when
        OAuthMember result = kakaoOAuthMemberConverter.convert(oAuth2User);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
