package com.gyeryongbrother.pickandtest.member.dataaccess.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberEntityTest {

    @Test
    @DisplayName("회원 엔티티를 회원으로 변환한다")
    void memberEntityToMember() {
        // given
        MemberEntity memberEntity = MemberEntity.builder()
                .id(1L)
                .nickname("nickname")
                .build();
        Member expected = new Member(1L, null, "nickname", null);

        // when
        Member result = memberEntity.toDomain();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
