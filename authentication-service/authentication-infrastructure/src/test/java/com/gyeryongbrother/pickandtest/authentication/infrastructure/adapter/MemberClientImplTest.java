package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.MemberClient;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.MemberServiceApiFetcher;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto.RegisterMemberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원 클라이언트를 구현한다")
class MemberClientImplTest {

    @Mock
    private MemberServiceApiFetcher memberServiceApiFetcher;

    private MemberClient memberClient;

    @BeforeEach
    void setUp() {
        memberClient = new MemberClientImpl(memberServiceApiFetcher);
    }

    @Test
    @DisplayName("회원을 등록한다")
    void registerMember() {
        // given
        given(memberServiceApiFetcher.registerMember(any()))
                .willReturn(new RegisterMemberResponse(1L, "nickname", "profileImageUrl"));
        Member member = new Member("nickname", "profileImageUrl");
        Member expected = new Member(1L, "nickname", "profileImageUrl");

        // when
        Member result = memberClient.registerMember(member);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
