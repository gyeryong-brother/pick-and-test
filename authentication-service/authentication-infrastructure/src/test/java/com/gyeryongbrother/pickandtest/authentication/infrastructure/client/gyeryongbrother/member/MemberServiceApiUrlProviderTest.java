package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("MemberService api url 을 제공한다")
class MemberServiceApiUrlProviderTest {

    private MemberServiceApiUrlProvider memberServiceApiUrlProvider;

    @BeforeEach
    void setUp() {
        memberServiceApiUrlProvider = new MemberServiceApiUrlProvider(
                "http://api.pickandtest.com"
        );
    }

    @Test
    @DisplayName("회원가입 url 을 가져온다")
    void getRegisterMemberUrl() {
        // given
        String expected = "http://api.pickandtest.com/member-service/members";

        // when
        String result = memberServiceApiUrlProvider.getRegisterMemberUrl();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
