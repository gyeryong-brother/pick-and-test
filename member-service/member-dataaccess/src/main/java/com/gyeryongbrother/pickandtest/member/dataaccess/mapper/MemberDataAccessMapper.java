package com.gyeryongbrother.pickandtest.member.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataAccessMapper {

    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .name(member.getName())
                .username(member.getUsername())
                .password(member.getPassword())
                .refreshToken(member.getRefreshToken())
                .userRole(member.getUserRole())
                .build();
    }

    public Member memberEntityToMember(MemberEntity memberEntity) {
        return Member.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .username(memberEntity.getUsername())
                .password(memberEntity.getPassword())
                .refreshToken(memberEntity.getRefreshToken())
                .build();
    }
}
