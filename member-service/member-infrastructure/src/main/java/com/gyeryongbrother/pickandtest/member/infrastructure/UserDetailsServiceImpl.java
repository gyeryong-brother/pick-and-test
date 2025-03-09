package com.gyeryongbrother.pickandtest.member.infrastructure;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberQueryRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member=memberQueryRepository
                .findByUsername(username)
                .orElseThrow(()->new RuntimeException("사용자가 존재하지 않습니다"));
        return new UserDetailsImpl(member);
    }
}
