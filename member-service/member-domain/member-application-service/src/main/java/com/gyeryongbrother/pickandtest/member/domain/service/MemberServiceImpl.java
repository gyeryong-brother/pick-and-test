package com.gyeryongbrother.pickandtest.member.domain.service;

import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.INCORRECT_PASSWORD;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_ID_EXISTS;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceException;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.JwtUtil;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterMemberResponse register(RegisterMemberCommand registerMemberCommand) {
        Member member = registerMemberCommand.toDomain();
        validateUserAlreadyExists(member.getUsername());
        Member memberWithEncodedPassword = Member.builder()
                .name(member.getName())
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .userRole(member.getUserRole())
                .build();
        Member registeredMember = memberRepository.save(memberWithEncodedPassword);
        RegisterMemberResponse registerMemberResponse = new RegisterMemberResponse(registeredMember.getName());
        return registerMemberResponse;
    }

    private void validateUserAlreadyExists(String username) {
        Optional<Member> optionalMember = memberQueryRepository.findByUsername(username);
        optionalMember.ifPresent(member -> {
            throw new MemberServiceException(USER_ID_EXISTS);
        });
    }

    @Override
    public LoginResponse login(LoginCommand loginCommand) {
        Member member = memberQueryRepository.getByUsername(loginCommand.username());
        if (!passwordEncoder.matches(loginCommand.password(), member.getPassword())) {
            throw new MemberServiceException(INCORRECT_PASSWORD);
        }
        String accessToken = jwtUtil.generateAccessToken(member.getId(),
                member.getUserRole());
        String refreshToken = jwtUtil.generateRefreshToken(member.getId());
        RefreshToken saved = refreshTokenRepository.save(
                new RefreshToken(null, member.getUsername(), refreshToken));
        LoginResponse loginResponse = new LoginResponse(accessToken, saved.getRefreshToken());
        return loginResponse;
    }
}
