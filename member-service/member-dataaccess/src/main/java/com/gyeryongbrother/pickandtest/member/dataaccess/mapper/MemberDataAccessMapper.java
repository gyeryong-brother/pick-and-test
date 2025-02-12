package com.gyeryongbrother.pickandtest.member.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataAccessMapper {

    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .name(member.getName())
                .refreshToken(member.getRefreshToken())
                .build();
    }

    public Member memberEntityToMember(MemberEntity memberEntity) {
        return Member.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .refreshToken(memberEntity.getRefreshToken())
                .build();
    }
}
