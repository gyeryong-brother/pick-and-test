package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberRequest;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
@DisplayName("MemberService api 를 호출한다")
class MemberServiceApiFetcherTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MemberServiceApiUrlProvider memberServiceApiUrlProvider;

    private MemberServiceApiFetcher memberServiceApiFetcher;

    @BeforeEach
    void setUp() {
        memberServiceApiFetcher = new MemberServiceApiFetcher(
                restTemplate,
                memberServiceApiUrlProvider
        );
    }

    @Test
    @DisplayName("회원가입 api 를 호출한다")
    void registerMember() {
        // given
        RegisterMemberRequest request = new RegisterMemberRequest("nickname", "profileImageUrl");
        RegisterMemberResponse response = new RegisterMemberResponse(1L, "nickname", "profileImageUrl");
        given(memberServiceApiUrlProvider.getRegisterMemberUrl())
                .willReturn("url");
        given(restTemplate.postForEntity(anyString(), any(), any()))
                .willReturn(ResponseEntity.ok(response));

        // when
        RegisterMemberResponse result = memberServiceApiFetcher.registerMember(request);

        // then
        verify(restTemplate).postForEntity(eq("url"), eq(request), eq(RegisterMemberResponse.class));
        assertThat(result).isEqualTo(response);
    }
}
