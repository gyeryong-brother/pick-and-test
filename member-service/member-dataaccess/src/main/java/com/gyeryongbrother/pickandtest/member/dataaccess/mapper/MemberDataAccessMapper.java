package com.gyeryongbrother.pickandtest.member.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDataAccessMapper {

    public MemberEntity memberToMemberEntity(Member member) {
        return MemberEntity.builder()
                .id(member.id())
                .memberRole(member.memberRole())
                .nickname(member.nickname())
                .profileImageUrl(member.profileImageUrl())
                .build();
    }
}
