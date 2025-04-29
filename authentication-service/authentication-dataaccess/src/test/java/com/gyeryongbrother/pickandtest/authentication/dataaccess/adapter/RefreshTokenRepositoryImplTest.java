package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
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
    private RefreshTokenQueryRepository refreshTokenQueryRepository;

    @Test
    @DisplayName("refreshToken을 저장한다")
    void save() {
        //given
        RefreshToken refreshToken = new RefreshToken(1L, "refreshToken");

        //when
        RefreshToken result = refreshTokenRepository.save(refreshToken);

        //then
        assertAll(
                () -> assertThat(result.id()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(refreshToken)
        );
    }

    @Test
    @DisplayName("refreshToken을 삭제한다")
    void delete() {
        //given
        RefreshToken saved = refreshTokenRepository.save(new RefreshToken(1L, "refreshToken"));

        //when
        refreshTokenRepository.deleteByToken("refreshToken");
        List<RefreshToken> result = refreshTokenQueryRepository.findByMemberId(1L);

        //then
        assertThat(result).isEmpty();
    }
}
