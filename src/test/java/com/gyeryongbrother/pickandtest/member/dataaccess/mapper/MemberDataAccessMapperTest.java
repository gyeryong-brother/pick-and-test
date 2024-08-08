package com.gyeryongbrother.pickandtest.member.dataaccess.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("회원과 회원 엔티티를 매핑한다")
class MemberDataAccessMapperTest {

    private MemberDataAccessMapper memberDataAccessMapper;

    @BeforeEach
    void setUp() {
        memberDataAccessMapper = new MemberDataAccessMapper();
    }

    @Test
    @DisplayName("회원을 회원 엔티티로 변환한다")
    void memberToMemberEntity() {
        // given
        Member member = Member.builder()
                .name("name")
                .build();
        MemberEntity expected = MemberEntity.builder()
                .name("name")
                .build();

        // when
        MemberEntity result = memberDataAccessMapper.memberToMemberEntity(member);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("회원 엔티티를 회원으로 변환한다")
    void memberEntityToMember() {
        // given
        MemberEntity memberEntity = MemberEntity.builder()
                .id(1L)
                .name("name")
                .build();
        Member expected = Member.builder()
                .id(1L)
                .name("name")
                .build();

        // when
        Member result = memberDataAccessMapper.memberEntityToMember(memberEntity);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
