package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.IncomeStatementFixture.appleIncomeStatement;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.IncomeStatement;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.IncomeStatementRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("기업 실적 정보 리포지토리를 구현한다")
class IncomeStatementRepositoryImplTest {

    @Autowired
    private IncomeStatementRepository incomeStatementRepository;

    @Test
    @DisplayName("기업 실적정보를 저장한다")
    void save() {
        //given
        IncomeStatement incomeStatement = appleIncomeStatement();
        IncomeStatement expected = appleIncomeStatement();

        //when
        IncomeStatement result = incomeStatementRepository.save(incomeStatement);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
