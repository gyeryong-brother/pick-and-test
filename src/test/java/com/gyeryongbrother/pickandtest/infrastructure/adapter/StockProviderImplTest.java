package com.gyeryongbrother.pickandtest.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.domain.core.StockExchange.NASDAQ;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.microsoftDividendResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.dividend.dto.DividendResponseFixture.nvidiaDividendResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockDetailFixture.apple;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockDetailFixture.microsoft;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockDetailFixture.nvidia;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.appleStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.microsoftStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.StockResponseFixture.nvidiaStockResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleFirstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleSecondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.appleThirdStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.microsoftFirstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.microsoftSecondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.microsoftThirdStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.nvidiaFirstStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.nvidiaSecondStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.dto.StockPriceResponseFixture.nvidiaThirdStockPriceResponse;
import static com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.stocksymbol.dto.StockSymbolResponseFixture.stockSymbolResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.BigDecimalComparator.BIG_DECIMAL_COMPARATOR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.domain.core.StockDetail;
import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockProvider;
import com.gyeryongbrother.pickandtest.infrastructure.client.alphavantage.AlphaVantageClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.KoreaInvestmentClient;
import com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.NasdaqClient;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.DividendFetcherDataMapper;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockFetcherDataMapper;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.StockPriceFetcherDataMapper;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("주식 정보를 제공한다")
class StockProviderImplTest {

    @Mock
    private KoreaInvestmentClient koreaInvestmentClient;

    @Mock
    private AlphaVantageClient alphaVantageClient;

    @Mock
    private NasdaqClient nasdaqClient;

    private StockProvider stockProvider;

    @BeforeEach
    void setUp() {
        StockFetcherDataMapper stockFetcherDataMapper = new StockFetcherDataMapper(
                new StockPriceFetcherDataMapper(),
                new DividendFetcherDataMapper()
        );
        stockProvider = new StockProviderImpl(
                koreaInvestmentClient,
                alphaVantageClient,
                nasdaqClient,
                stockFetcherDataMapper
        );
    }

    @Test
    @DisplayName("주식거래소에 상장되어 있는 모든 주식들을 가져온다")
    void getStockDetailsByStockExchange() {
        // given
        given(nasdaqClient.fetchStockSymbol(any(StockExchange.class)))
                .willReturn(stockSymbolResponse());
        given(koreaInvestmentClient.fetchStock(any(StockExchange.class), anyString())).willReturn(
                appleStockResponse(),
                microsoftStockResponse(),
                nvidiaStockResponse()
        );
        given(koreaInvestmentClient.fetchStockPrice(any(), anyString(), any())).willReturn(
                appleFirstStockPriceResponse(),
                appleSecondStockPriceResponse(),
                appleThirdStockPriceResponse(),
                microsoftFirstStockPriceResponse(),
                microsoftSecondStockPriceResponse(),
                microsoftThirdStockPriceResponse(),
                nvidiaFirstStockPriceResponse(),
                nvidiaSecondStockPriceResponse(),
                nvidiaThirdStockPriceResponse()
        );
        given(alphaVantageClient.fetchDividend(anyString())).willReturn(
                appleDividendResponse(),
                microsoftDividendResponse(),
                nvidiaDividendResponse()
        );
        List<StockDetail> expected = List.of(
                apple(),
                microsoft(),
                nvidia()
        );

        // when
        List<StockDetail> result = stockProvider.getStockDetailsByStockExchange(NASDAQ);

        // then
        assertThat(result).usingRecursiveComparison()
                .withComparatorForType(BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expected);
    }
}
