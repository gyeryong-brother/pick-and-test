package com.gyeryongbrother.pickandtest.domain.service;

import static com.gyeryongbrother.pickandtest.domain.core.FavoriteStockFixture.favoriteStock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.StockService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.FavoriteStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식 서비스를 구현한다")
class StockServiceImplTest {

    @Mock
    private FavoriteStockRepository favoriteStockRepository;

    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockService = new StockServiceImpl(favoriteStockRepository);
    }

    @Test
    @DisplayName("관심 주식을 저장한다")
    void createFavoriteStock() {
        // given
        CreateFavoriteStockCommand createFavoriteStockCommand = new CreateFavoriteStockCommand(1L, 1L);
        given(favoriteStockRepository.save(any()))
                .willReturn(favoriteStock(1L));
        CreateFavoriteStockResponse expected = new CreateFavoriteStockResponse(1L, 1L, 1L);

        // when
        CreateFavoriteStockResponse result = stockService.createFavoriteStock(createFavoriteStockCommand);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
