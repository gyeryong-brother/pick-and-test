package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.gyeryongbrother;

import static com.gyeryongbrother.pickandtest.stockprice.domain.core.valueobject.StockExchange.NGM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.common.FetcherSupport;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.StockServiceClient;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.StockServiceUrlProvider;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.gyeryongbrother.dto.StockResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식을 가져온다")
class StockServiceClientTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private StockServiceClient stockServiceClient;

    @BeforeEach
    void setUp() {
        stockServiceClient = new StockServiceClient(new StockServiceUrlProvider("http://domain"), fetcherSupport);
    }

    @Test
    @DisplayName("주식을 가져온다")
    void fetchStock() {
        // given
        given(fetcherSupport.get(anyString(), any()))
                .willReturn(new StockResponse(1L, "AAPL", "NGM"));
        Stock expected = new Stock(1L, "AAPL", NGM);

        // when
        Stock result = stockServiceClient.fetchStock(1L);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
