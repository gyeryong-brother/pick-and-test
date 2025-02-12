package com.gyeryongbrother.pickandtest.member.domain.service;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
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

    @Override
    public RegisterMemberResponse register(RegisterMemberCommand registerMemberCommand) {
        Member member = registerMemberCommand.toDomain();
        Member registeredMember = memberRepository.save(member);
        return new RegisterMemberResponse("accessToken","refreshToken");
    }
}
