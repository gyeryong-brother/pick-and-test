package com.gyeryongbrother.pickandtest.stock.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.FavoriteStockEntityFixture.favoriteStockEntity;
import static com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntityFixture.stockEntity;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.FavoriteStockFixture.favoriteStocks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;

import com.gyeryongbrother.pickandtest.stock.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockDetailEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.FavoriteStockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockDetailJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockJpaRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
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
@DisplayName("관심 주식 조회 레포지토리를 구현한다")
class FavoriteStockQueryRepositoryImplTest {

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private StockDetailJpaRepository stockDetailJpaRepository;

    @Autowired
    private FavoriteStockJpaRepository favoriteStockJpaRepository;

    @Autowired
    private FavoriteStockQueryRepository favoriteStockQueryRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("회원 아이디로 관심 주식들을 조회한다")
    void findAllByMemberId() {
        // given
        StockEntity firstStockEntity = stockEntity();
        StockEntity secondStockEntity = stockEntity();
        stockJpaRepository.saveAll(List.of(firstStockEntity, secondStockEntity));
        favoriteStockJpaRepository.saveAll(List.of(
                favoriteStockEntity(firstStockEntity),
                favoriteStockEntity(secondStockEntity)
        ));
        List<FavoriteStock> expected = favoriteStocks(firstStockEntity.getId(), secondStockEntity.getId());

        // when
        List<FavoriteStock> result = favoriteStockQueryRepository.findAllByMemberId(1L);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("주식 상세정보까지 함께 조회한다")
    void findAllByMemberIdWithStockDetail() {
        // given
        StockEntity firstStockEntity = stockEntity();
        StockEntity secondStockEntity = stockEntity();
        stockJpaRepository.saveAll(List.of(firstStockEntity, secondStockEntity));
        StockDetailEntity firstStockDetailEntity = new StockDetailEntity(
                null,
                firstStockEntity,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(58.49),
                BigDecimal.valueOf(0.2)
        );
        StockDetailEntity secondStockDetailEntity = new StockDetailEntity(
                null,
                secondStockEntity,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0.5)
        );
        stockDetailJpaRepository.saveAll(List.of(firstStockDetailEntity, secondStockDetailEntity));
        favoriteStockJpaRepository.saveAll(List.of(
                favoriteStockEntity(firstStockEntity),
                favoriteStockEntity(secondStockEntity)
        ));
        entityManager.clear();
        List<FavoriteStock> expected = favoriteStocks(firstStockEntity.getId(), secondStockEntity.getId());

        // when
        List<FavoriteStock> result = favoriteStockQueryRepository.findAllByMemberId(1L);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
