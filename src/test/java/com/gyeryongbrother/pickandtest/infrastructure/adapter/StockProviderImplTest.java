package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponseFixture.actualFetchStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.firstFetchStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.secondFetchStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.FetchStockPriceResponseFixture.thirdFetchStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.FetchStockResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StockProviderImplTest {

    @Mock
    private KoreaInvestmentClient koreaInvestmentClient;

    private StockProvider stockProvider;

    @BeforeEach
    void setUp() {
        stockProvider = new StockProviderImpl(koreaInvestmentClient);
    }

    @Test
    void provide() {
        // given
        FetchStockResponse fetchStockResponse = actualFetchStockResponse();
        given(koreaInvestmentClient.fetchStock(any(StockExchange.class), anyString()))
                .willReturn(fetchStockResponse);
        given(koreaInvestmentClient.fetchStockPrice(any(), anyString(), any())).willReturn(
                firstFetchStockPriceResponse(),
                secondFetchStockPriceResponse(),
                thirdFetchStockPriceResponse()
        );
        List<StockPrice> expectedStockPrices = List.of(
                stockPrice(12, 230.54),
                stockPrice(11, 227.57),
                stockPrice(10, 232.98),
                stockPrice(9, 228.68),
                stockPrice(8, 227.82),
                stockPrice(5, 226.34),
                stockPrice(3, 221.55),
                stockPrice(2, 220.27)
        );
        Stock expected = Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .listingDate(null)
                .stockPrices(expectedStockPrices)
                .build();

        // when
        Stock result = stockProvider.provide("AAPL");

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }

    private StockPrice stockPrice(int day, double price) {
        return StockPrice.builder()
                .date(LocalDate.of(2024, 7, day))
                .price(BigDecimal.valueOf(price))
                .build();
    }
}
