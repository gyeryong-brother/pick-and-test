package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.member.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.member.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.RefreshTokenJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenRepository;
import java.util.List;
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

    @Autowired
    private RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Test
    @DisplayName("refreshToken을 저장한다")
    void save() {
        //given
        RefreshToken refreshToken = RefreshToken.builder()
                .username("username")
                .token("refreshToken")
                .build();

        //when
        RefreshToken result = refreshTokenRepository.save(refreshToken);

        //then
        assertAll(
                () -> assertThat(result.getId()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(refreshToken)
        );
    }

    @Test
    @DisplayName("refreshToken을 삭제한다")
    void delete(){
        //given
        RefreshToken refreshToken = RefreshToken.builder()
                .username("username")
                .token("refreshToken")
                .build();
        RefreshToken saved = refreshTokenRepository.save(refreshToken);

        //when
        List<RefreshTokenEntity> pre=refreshTokenJpaRepository.findAll();

        long result=refreshTokenRepository.delete("refreshToken");
        long expected=1L;

        List<RefreshTokenEntity> post=refreshTokenJpaRepository.findAll();

        //then
        assertThat(result).isEqualTo(expected);
    }
}
