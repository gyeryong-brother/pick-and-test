package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.member.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("refreshToken 레포지토리를 구현한다")
public class RefreshTokenRepositoryImplTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    @DisplayName("refreshToken을 저장한다")
    void save(){
        //given
        RefreshToken refreshToken=RefreshToken.builder()
                .username("username")
                .refreshToken("refreshToken")
                .build();

        //when
        RefreshToken result=refreshTokenRepository.save(refreshToken);

        //then
        assertAll(
                () -> assertThat(result.getId()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(refreshToken)
        );
    }
}
