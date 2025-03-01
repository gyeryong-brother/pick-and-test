package com.gyeryongbrother.pickandtest.member.domain.service;

import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.INCORRECT_PASSWORD;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_ID_EXISTS;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceException;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterMemberResponse register(RegisterMemberCommand registerMemberCommand) {
        Member member = registerMemberCommand.toDomain();
        validateUserAlreadyExists(member.getUsername());
        Member memberWithEncodedPassword=Member.builder()
                .name(member.getName())
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .userRole(member.getUserRole())
                .build();
        Member registeredMember = memberRepository.save(memberWithEncodedPassword);
        RegisterMemberResponse registerMemberResponse = new RegisterMemberResponse(memberWithEncodedPassword.getName());
        return registerMemberResponse;
    }

    private void validateUserAlreadyExists(String username){
        try {
            memberQueryRepository.findByUsername(username);
        } catch (MemberServiceException memberServiceException){
            return;
        }
        throw new MemberServiceException(USER_ID_EXISTS);
    }

    @Override
    public LoginResponse login(LoginCommand loginCommand) {
        Member memberWithEncodedPassword = memberQueryRepository.findByUsername(loginCommand.username());
        if (!passwordEncoder.matches(loginCommand.password(), memberWithEncodedPassword.getPassword())) {
            throw new MemberServiceException(INCORRECT_PASSWORD);
        }
        String accessToken = jwtUtil.generateAccessToken(memberWithEncodedPassword.getId(), memberWithEncodedPassword.getUserRole());
        String refreshToken = jwtUtil.generateRefreshToken(memberWithEncodedPassword.getId());
        memberRepository.updateRefreshToken(memberWithEncodedPassword.getId(), refreshToken);
        LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }
}
