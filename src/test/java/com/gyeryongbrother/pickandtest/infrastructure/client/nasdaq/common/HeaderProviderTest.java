package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

class HeaderProviderTest {

    private HeaderProvider headerProvider;

    @BeforeEach
    void setUp() {
        headerProvider = new HeaderProvider();
    }

    @Test
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
