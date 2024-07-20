package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

@ExtendWith(MockitoExtension.class)
class AuthManagerTest {

    @Mock
    private AccessTokenManager accessTokenManager;

    private AuthManager authManager;

    @BeforeEach
    void setUp() {
        authManager = new AuthManager(accessTokenManager, new KoreaInvestmentClientCredential("appKey", "appSecret"));
    }

    @Test
    void createAuthHttpHeaders() {
        // given
        given(accessTokenManager.getAccessToken()).willReturn("accessToken");
        HttpHeaders expected = new HttpHeaders();
        expected.setBearerAuth("accessToken");
        expected.set("appkey", "appKey");
        expected.set("appsecret", "appSecret");

        // when
        HttpHeaders result = authManager.createAuthHttpHeaders();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
