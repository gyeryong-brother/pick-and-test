package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("refreshToken 쿼리 레포지토리를 구현한다")
public class RefreshTokenQueryRepositoryImplTest {

    @Autowired
    private RefreshTokenQueryRepository refreshTokenQueryRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    @DisplayName("회원 아이디로 리프레시 토큰들을 찾는다")
    void findByUsername() {
        //given
        RefreshToken refreshToken = refreshTokenRepository.save(new RefreshToken(1L, "refreshToken"));

        //when
        List<RefreshToken> refreshTokens = refreshTokenQueryRepository.findByMemberId(1L);
        RefreshToken result = refreshTokens.get(0);

        //then
        assertAll(
                () -> Assertions.assertThat(result.id()).isPositive(),
                () -> Assertions.assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(refreshToken)
        );
    }
}
