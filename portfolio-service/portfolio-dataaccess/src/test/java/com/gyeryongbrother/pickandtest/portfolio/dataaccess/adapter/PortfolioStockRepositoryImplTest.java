package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.portfolio.dataaccess.exception.PortfolioExceptionType.PORTFOLIO_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioFixture;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.exception.PortfolioException;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오주식 리퍼지토리를 구현한다")
class PortfolioStockRepositoryImplTest {

    @Autowired
    private PortfolioStockRepository portfolioStockRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioStockJpaRepository portfolioStockJpaRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("포트폴리오주식을 저장한다")
    void save() {
        //given
        PortfolioStock appleInPortfolio = PortfolioStock.builder().stockId(1L).portion(BigDecimal.valueOf(0.5))
                .build();

        //when
        PortfolioStock result = portfolioStockRepository.save(appleInPortfolio);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(appleInPortfolio);
    }

    @Test
    @DisplayName("포트폴리오 Id로 포트폴리오주식을 전부 삭제")
    void deleteByPortfolioId() {
        //given
        List<PortfolioStockEntity> initial = portfolioStockJpaRepository.findAll();
        Portfolio portfolio1 = PortfolioFixture.portfolio1();
        Portfolio savedPortfolio = portfolioRepository.save(portfolio1);
        entityManager.flush();
        entityManager.clear();

        entityManager.flush();
        entityManager.clear();

        //when
        portfolioStockRepository.deleteAllByPortfolioId(savedPortfolio.getId());
        List<PortfolioStockEntity> result = portfolioStockJpaRepository.findAll();
        List<PortfolioStockEntity> expected = List.of();

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("없는 Portfolio Id를 입력받는 경우에도 에러를 일으키지 않고 데이터베이스를 유지")
    void deleteByNonExistPortfolioId() {
        //given
        List<PortfolioStockEntity> initial = portfolioStockJpaRepository.findAll();
        Portfolio portfolio1 = PortfolioFixture.portfolio1();
        Portfolio savedPortfolio = portfolioRepository.save(portfolio1);

        //when
        BaseExceptionType result = assertThrows(PortfolioException.class,
                () -> portfolioStockRepository.deleteAllByPortfolioId(-1L)
        ).exceptionType();

        //then
        assertThat(result).isEqualTo(PORTFOLIO_NOT_FOUND);
    }
}
