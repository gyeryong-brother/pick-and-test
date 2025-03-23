package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.auth.AuthManager;
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
    @DisplayName("주식 api 용 헤더를 만든다")
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
}
