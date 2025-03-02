package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.member.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("회원 쿼리 레포지토리를 구현한다")
public class MemberQueryRepositoryImplTest {

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("username을 통해 회원을 찾는다")
    void findByUsername(){
        //given
        Member member=Member.builder()
                .username("username")
                .password("password")
                .build();

        memberRepository.save(member);

        //when
        Member result=memberQueryRepository.findByUsername(member.getUsername());

        //then
        assertAll(
                () -> assertThat(result.getId()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(member)
        );
    }
}
