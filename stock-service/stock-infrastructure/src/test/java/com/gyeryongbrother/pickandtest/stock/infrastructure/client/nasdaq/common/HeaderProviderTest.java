package com.gyeryongbrother.pickandtest.stock.infrastructure.client.nasdaq.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

@DisplayName("Nasdaq api 에서 사용되는 요청 헤더를 만든다")
class HeaderProviderTest {

    private HeaderProvider headerProvider;

    @BeforeEach
    void setUp() {
        headerProvider = new HeaderProvider();
    }

    @Test
    @DisplayName("주식 티커 api 용 헤더를 만든다")
    void getHeaders() {
        // given
        HttpHeaders expected = new HttpHeaders();
        expected.set("User-Agent", "PostmanRuntime/7.40.0");

        // when
        HttpHeaders result = headerProvider.getHeaders();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
