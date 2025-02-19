package com.gyeryongbrother.pickandtest.member.domain.service;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
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

    @Override
    public RegisterMemberResponse login(LoginCommand loginCommand) {
        Member member=memberQueryRepository.findByUserId(loginCommand.userId());
        if (!member.getPassword().equals(loginCommand.password())) {
            throw new RuntimeException("password is incorrect");
        }
        String accessToken= jwtUtil.generateAccessToken(member.getId(),UserRole.ROLE_USER);
        String refreshToken=jwtUtil.generateRefreshToken(member.getId());
        memberRepository.updateRefreshToken(member.getId(),refreshToken);
        RegisterMemberResponse registerMemberResponse=new RegisterMemberResponse(accessToken,refreshToken);
        return registerMemberResponse;
    }
}
