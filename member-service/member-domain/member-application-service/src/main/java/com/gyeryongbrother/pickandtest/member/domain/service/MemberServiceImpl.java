package com.gyeryongbrother.pickandtest.member.domain.service;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterMemberResponse register(RegisterMemberCommand registerMemberCommand) {
        Member member = registerMemberCommand.toDomain();
        Member registeredMember = memberRepository.save(member);
        String accessToken= jwtUtil.generateAccessToken(registeredMember.getId(),UserRole.ROLE_USER);
        String refreshToken=jwtUtil.generateRefreshToken(registeredMember.getId());
        memberRepository.updateRefreshToken(registeredMember.getId(),refreshToken);
        RegisterMemberResponse registerMemberResponse=new RegisterMemberResponse(accessToken,refreshToken);
        return registerMemberResponse;
    }
}
