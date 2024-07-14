package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.AuthManager;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice.ContinuityCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

@ExtendWith(MockitoExtension.class)
class HeaderHandlerTest {

    @Mock
    private AuthManager authManager;

    private HeaderHandler headerHandler;

    @BeforeEach
    void setUp() {
        headerHandler = new HeaderHandler(authManager);
    }

    @Test
    void getStockHeader() {
        // given
        HttpHeaders expected = new HttpHeaders();
        expected.set("tr_id", "CTPF1702R");
        given(authManager.createAuthHttpHeaders()).willReturn(new HttpHeaders());

        // when
        HttpHeaders result = headerHandler.getHeader(FetchType.STOCK);

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
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
    void parseContinuityCodeByEmpty() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();

        // when
        String result = assertThrows(IllegalStateException.class, () ->
                headerHandler.parseContinuityCode(httpHeaders)
        ).getMessage();

        // then
        assertThat(result).isEqualTo("can not be null");
    }

    @Test
    void parseContinuityCodeByTwoValue() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("tr_cont", "D");
        httpHeaders.add("tr_cont", "F");

        // when
        String result = assertThrows(IllegalStateException.class, () ->
                headerHandler.parseContinuityCode(httpHeaders)
        ).getMessage();

        // then
        assertThat(result).isEqualTo("should be only one value");
    }
}
