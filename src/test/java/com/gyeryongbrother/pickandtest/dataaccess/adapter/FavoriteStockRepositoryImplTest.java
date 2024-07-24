package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.domain.core.FavoriteStockFixture.favoriteStock;
import static com.gyeryongbrother.pickandtest.domain.core.StockFixture.stock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.domain.core.FavoriteStock;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("관심 주식 레포지토리를 구현한다")
class FavoriteStockRepositoryImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private FavoriteStockRepository favoriteStockRepository;

    @Test
    @DisplayName("관심 주식을 저장한다")
    void save() {
        // given
        Stock stock = stockRepository.save(stock(null));
        FavoriteStock favoriteStock = favoriteStock(stock);

        // when
        FavoriteStock result = favoriteStockRepository.save(favoriteStock);

        // then
        assertAll(
                () -> assertThat(result.getId()).isPositive(),
                () -> assertThat(result.getStock().getId()).isPositive(),
                () -> assertThat(result).usingRecursiveComparison()
                        .ignoringExpectedNullFields()
                        .isEqualTo(favoriteStock)
        );
    }
}
