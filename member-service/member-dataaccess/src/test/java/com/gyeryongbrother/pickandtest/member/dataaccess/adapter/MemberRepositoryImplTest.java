package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.member.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("회원 레포지토리를 구현한다")
class MemberRepositoryImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 저장한다")
    void save() {
        // given
        Member member = new Member("nickname", null);

        // when
        Member result = memberRepository.save(member);

        // then
        assertAll(
                () -> assertThat(result.id()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(member)
        );
    }
}
