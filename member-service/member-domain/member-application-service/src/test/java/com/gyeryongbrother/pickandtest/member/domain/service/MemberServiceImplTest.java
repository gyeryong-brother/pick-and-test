package com.gyeryongbrother.pickandtest.member.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원 서비스를 구현한다")
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    private MemberService memberService;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository,jwtUtil);
    }

    @Test
    @DisplayName("회원을 등록한다")
    void register() {
        // given
        Member member = Member.builder()
                .id(1L)
                .name("name")
                .build();
        given(memberRepository.save(any(Member.class)))
                .willReturn(member);
        given(jwtUtil.generateAccessToken(any(),any()))
                .willReturn("accessToken");
        given(jwtUtil.generateRefreshToken(any()))
                .willReturn("refreshToken");

        RegisterMemberCommand registerMemberCommand = new RegisterMemberCommand("name","userId","password");
        RegisterMemberResponse expected = new RegisterMemberResponse("accessToken", "refreshToken");

        // when
        RegisterMemberResponse result = memberService.register(registerMemberCommand);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
