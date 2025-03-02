package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.member.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenRepository;
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
    @DisplayName("username으로 refresh토큰들을 찾음")
    void findByUsername() {
        //given
        RefreshToken refreshToken = RefreshToken.builder()
                .username("username")
                .refreshToken("refreshToken")
                .build();

        RefreshToken saved = refreshTokenRepository.save(refreshToken);

        //when
        List<RefreshToken> refreshTokens = refreshTokenQueryRepository.findByUsername(refreshToken.getUsername());
        RefreshToken result = refreshTokens.get(0);

        //then
        assertAll(
                () -> Assertions.assertThat(result.getId()).isPositive(),
                () -> Assertions.assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(refreshToken)
        );
    }
}
