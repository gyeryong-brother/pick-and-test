package com.gyeryongbrother.pickandtest.stockprice.infrastructure.client.koreainvestment.common;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.HEADER_CAN_NOT_BE_NULL;
import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.HEADER_SHOULD_HAVE_ONLY_ONE_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stockprice.domain.service.exception.BaseExceptionType;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.auth.AuthManager;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.FetchType;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.common.HeaderHandler;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice.ContinuityCode;
import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

@ExtendWith(MockitoExtension.class)
@DisplayName("api 에서 사용되는 요청 헤더를 만들고 응답 헤더에서 데이터를 추출한다")
class HeaderHandlerTest {

    @Mock
    private AuthManager authManager;

    private HeaderHandler headerHandler;

    @BeforeEach
    void setUp() {
        headerHandler = new HeaderHandler(authManager);
    }

    @Test
    @DisplayName("주가 api 용 헤더를 만든다")
    void getStockPriceHeader() {
        // given
        HttpHeaders expected = new HttpHeaders();
        expected.set("tr_id", "HHDFS76240000");
        given(authManager.createAuthHttpHeaders()).willReturn(new HttpHeaders());

        // when
        HttpHeaders result = headerHandler.getHeader(FetchType.STOCK_PRICE);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("응답 헤더에 F 가 있을 때 NEXT 를 추출한다")
    void parseContinuityCodeByF() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("tr_cont", "F");

        // when
        ContinuityCode result = headerHandler.parseContinuityCode(httpHeaders);

        // then
        assertThat(result).isEqualTo(ContinuityCode.NEXT);
    }

    @Test
    @DisplayName("응답 헤더에 D 가 있을 때 END 를 추출한다")
    void parseContinuityCodeByD() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("tr_cont", "D");

        // when
        ContinuityCode result = headerHandler.parseContinuityCode(httpHeaders);

        // then
        assertThat(result).isEqualTo(ContinuityCode.END);
    }

    @Test
    @DisplayName("응답 헤더가 비어 있을 때 예외가 발생한다")
    void parseContinuityCodeByEmpty() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();

        // when
        BaseExceptionType result = assertThrows(StockPriceInfrastructureException.class, () ->
                headerHandler.parseContinuityCode(httpHeaders)
        ).exceptionType();

        // then
        assertThat(result).isEqualTo(HEADER_CAN_NOT_BE_NULL);
    }

    @Test
    @DisplayName("응답 헤더에 여러 값이 있으면 예외가 발생한다")
    void parseContinuityCodeByTwoValue() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("tr_cont", "D");
        httpHeaders.add("tr_cont", "F");

        // when
        BaseExceptionType result = assertThrows(StockPriceInfrastructureException.class, () ->
                headerHandler.parseContinuityCode(httpHeaders)
        ).exceptionType();

        // then
        assertThat(result).isEqualTo(HEADER_SHOULD_HAVE_ONLY_ONE_VALUE);
    }
}
