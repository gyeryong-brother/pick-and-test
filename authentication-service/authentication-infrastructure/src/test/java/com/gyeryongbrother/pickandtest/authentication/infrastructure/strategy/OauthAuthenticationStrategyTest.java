package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthorizationCodeAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OauthAuthenticationStrategyTest {

    @Autowired
    private OauthAuthenticationStrategy oauthAuthenticationStrategy;

    @Test
    void authenticate() {
        // given
        AuthorizationCodeAttempt attempt = new AuthorizationCodeAttempt(AuthenticationMethod.KAKAO,
                "D5ptpoSx2-fWd3T0Ri3VMyjRpSxKo2NVi2x0_d4fmgSij06i7VduQAAAAAQKDQgeAAABl3YXA-fHP8VuE1ZNOQ");

        // when
        Tokens tokens = oauthAuthenticationStrategy.authenticate(attempt);

        // then
        System.out.println(tokens);
    }
}
