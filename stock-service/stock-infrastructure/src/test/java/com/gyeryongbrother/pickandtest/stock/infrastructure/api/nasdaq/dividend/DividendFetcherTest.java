package com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto.DividendResponseFixture.appleDividendResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.FetcherSupport;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common.HeaderProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.common.NasdaqUrlProvider;
import com.gyeryongbrother.pickandtest.stock.infrastructure.api.nasdaq.dividend.dto.DividendResponse;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("배당 정보를 가져온다")
class DividendFetcherTest {

    @Mock
    private FetcherSupport fetcherSupport;

    private DividendFetcher dividendFetcher;

    @BeforeEach
    void setUp() {
        dividendFetcher = new DividendFetcher(new NasdaqUrlProvider(), new HeaderProvider(), fetcherSupport);
    }

    @Test
    @DisplayName("배당 정보를 가져온다")
    void fetchDividend() {
        // given
        DividendResponse appleDividendResponse = appleDividendResponse();
        given(fetcherSupport.get(any(URI.class), any(), any()))
                .willReturn(ResponseEntity.ok(appleDividendResponse));

        // when
        DividendResponse result = dividendFetcher.fetchDividend("AAPL", "stocks");

        // then
        assertThat(result).isEqualTo(appleDividendResponse);
    }
}
