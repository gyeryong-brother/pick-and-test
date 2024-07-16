package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.dividendResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.actualStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.firstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.secondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.thirdStockPriceResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.core.StockPrice;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.AlphaVantageClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockExchange;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponse;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.DividendFetcherDataMapper;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockFetcherDataMapper;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockPriceFetcherDataMapper;
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

    @Mock
    private AlphaVantageClient alphaVantageClient;

    private StockProvider stockProvider;

    @BeforeEach
    void setUp() {
        StockFetcherDataMapper stockFetcherDataMapper = new StockFetcherDataMapper(
                new StockPriceFetcherDataMapper(),
                new DividendFetcherDataMapper()
        );
        stockProvider = new StockProviderImpl(koreaInvestmentClient, alphaVantageClient, stockFetcherDataMapper);
    }

    @Test
    void provide() {
        // given
        StockResponse stockResponse = actualStockResponse();
        DividendResponse dividendResponse = dividendResponse();
        given(koreaInvestmentClient.fetchStock(any(StockExchange.class), anyString()))
                .willReturn(stockResponse);
        given(koreaInvestmentClient.fetchStockPrice(any(), anyString(), any())).willReturn(
                firstStockPriceResponse(),
                secondStockPriceResponse(),
                thirdStockPriceResponse()
        );
        given(alphaVantageClient.fetchDividend(anyString()))
                .willReturn(dividendResponse);
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
        List<Dividend> expectedDividends = List.of(
                dividend(8, 0.24),
                dividend(5, 0.24),
                dividend(2, 0.23)
        );
        Stock expected = Stock.builder()
                .name("APPLE INC")
                .symbol("AAPL")
                .listingDate(null)
                .stockPrices(expectedStockPrices)
                .dividends(expectedDividends)
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

    private Dividend dividend(int month, double amount) {
        return Dividend.builder()
                .date(LocalDate.of(2023, month, 10))
                .amount(BigDecimal.valueOf(amount))
                .build();
    }
}
