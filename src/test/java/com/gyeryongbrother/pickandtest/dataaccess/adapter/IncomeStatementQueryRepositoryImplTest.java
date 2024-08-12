package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntityFixture.incomeStatementEntity;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementFixture.incomeStatement;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.domain.core.LocalDateFixture.januaryThird;
import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntityFixture;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.StockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.IncomeStatementJpaRepository;
import com.gyeryongbrother.pickandtest.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.IncomeStatement;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.IncomeStatementQueryRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("기업 실적 조회 리포지토리를 구현한다")
class IncomeStatementQueryRepositoryImplTest {

    @Autowired
    private IncomeStatementJpaRepository incomeStatementJpaRepository;
    @Autowired
    private IncomeStatementQueryRepository incomeStatementQueryRepository;
    @Autowired
    private StockJpaRepository stockJpaRepository;
    @Autowired
    private StockDataAccessMapper stockDataAccessMapper;


    @Test
    @DisplayName("Stock으로 해당 주식의 실적 정보를 모두 불러온다")
    void findAllbyStock() {
        //given
        StockEntity appleEntity = stockJpaRepository.save(StockEntityFixture.stockEntity());
        StockEntity nvidiaEntity = stockJpaRepository.save(StockEntityFixture.nvidiaStockEntity());
        Stock apple = stockDataAccessMapper.stockEntityToStock(appleEntity);
        List<IncomeStatementEntity> appleIncomeStatementEntities = List.of(
                incomeStatementEntity(appleEntity, 200L, 100L, 50L,
                        januaryFirst()),
                incomeStatementEntity(appleEntity, 220L, 100L, 50L,
                        januarySecond())
        );
        List<IncomeStatementEntity> nvidiaIncomeStatementEntities = List.of(
                incomeStatementEntity(nvidiaEntity, 100L, 50L, 20L,
                        januaryThird())
        );
        List<IncomeStatement> expected = List.of(
                incomeStatement(apple, 200L, 100L, 50L, januaryFirst()),
                incomeStatement(apple, 220L, 100L, 50L, januarySecond())
        );
        incomeStatementJpaRepository.saveAll(appleIncomeStatementEntities);
        incomeStatementJpaRepository.saveAll(nvidiaIncomeStatementEntities);

        //when
        List<IncomeStatement> result = incomeStatementQueryRepository.findAllByStockId(apple.getId());

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
