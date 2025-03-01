package com.gyeryongbrother.pickandtest.member.domain.service;

import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.INCORRECT_PASSWORD;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_ID_EXISTS;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceException;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
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
        try {
            memberQueryRepository.findByUsername(member.getUsername());
        } catch (MemberServiceException memberServiceException) {
            Member registeredMember = memberRepository.save(member);
            RegisterMemberResponse registerMemberResponse = new RegisterMemberResponse(member.getName());
            return registerMemberResponse;
        }
        throw new MemberServiceException(USER_ID_EXISTS);
    }

    @Override
    public LoginResponse login(LoginCommand loginCommand) {
        Member member = memberQueryRepository.findByUsername(loginCommand.username());
        if (!member.getPassword().equals(loginCommand.password())) {
            throw new MemberServiceException(INCORRECT_PASSWORD);
        }
        String accessToken = jwtUtil.generateAccessToken(member.getId(), UserRole.ROLE_USER);
        String refreshToken = jwtUtil.generateRefreshToken(member.getId());
        memberRepository.updateRefreshToken(member.getId(), refreshToken);
        LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }
}
