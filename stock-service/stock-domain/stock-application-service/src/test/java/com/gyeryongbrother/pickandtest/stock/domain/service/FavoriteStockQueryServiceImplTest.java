package com.gyeryongbrother.pickandtest.stock.domain.service;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.entity.FavoriteStockFixture.favoriteStocks;
import static com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponseFixture.favoriteStockResponses;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.FavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.FavoriteStockQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("관심 주식 조회 서비스를 구현한다")
class FavoriteStockQueryServiceImplTest {

    @Mock
    private FavoriteStockQueryRepository favoriteStockQueryRepository;

    private FavoriteStockQueryService favoriteStockQueryService;

    @BeforeEach
    void setUp() {
        favoriteStockQueryService = new FavoriteStockQueryServiceImpl(favoriteStockQueryRepository);
    }

    @Test
    @DisplayName("회원 아이디로 관심 주식들을 조회한다")
    void findAllFavoriteStocksByMemberId() {
        // given
        List<FavoriteStock> favoriteStocks = favoriteStocks();
        given(favoriteStockQueryRepository.findAllByMemberId(anyLong()))
                .willReturn(favoriteStocks);
        List<FavoriteStockResponse> expected = favoriteStockResponses();

        // when
        List<FavoriteStockResponse> result = favoriteStockQueryService.findAllFavoriteStocksByMemberId(1L);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
