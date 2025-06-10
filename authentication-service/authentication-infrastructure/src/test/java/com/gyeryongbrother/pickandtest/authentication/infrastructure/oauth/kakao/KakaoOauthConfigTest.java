package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth.kakao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoOauthConfigTest {

    @Autowired
    private KakaoOauthConfig kakaoOauthConfig;

    @Test
    @DisplayName("카카오 OAuth 클라이언트 아이디를 불러온다")
    void clientId() {
        // when
        String result = kakaoOauthConfig.clientId();

        // then
        assertThat(result).isEqualTo("client_id");
    }

    @Test
    @DisplayName("카카오 OAuth 리다이렉트 URI 를 불러온다")
    void redirectUri() {
        // when
        String result = kakaoOauthConfig.redirectUri();

        // then
        assertThat(result).isEqualTo("http://localhost:3000/oauth/kakao");
    }

    @Test
    @DisplayName("카카오 OAuth 클라이언트 시크릿을 불러온다")
    void clientSecret() {
        // when
        String result = kakaoOauthConfig.clientSecret();

        // then
        assertThat(result).isEqualTo("client_secret");
    }

    @Test
    @DisplayName("카카오 OAuth 스코프를 불러온다")
    void scope() {
        // given
        List<String> expected = List.of("profile_nickname", "profile_image");

        // when
        List<String> result = kakaoOauthConfig.scope();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
