package com.gyeryongbrother.pickandtest.member.domain.service;

import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_ID_EXISTS;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceException;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
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
        return new RegisterMemberResponse(registeredMember.getName());
    }

    private void validateUserAlreadyExists(String username) {
        Optional<Member> optionalMember = memberQueryRepository.findByUsername(username);
        optionalMember.ifPresent(member -> {
            throw new MemberServiceException(USER_ID_EXISTS);
        });
    }
}
