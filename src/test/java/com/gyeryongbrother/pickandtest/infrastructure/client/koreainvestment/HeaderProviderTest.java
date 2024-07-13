package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.auth.AuthManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

@ExtendWith(MockitoExtension.class)
class HeaderProviderTest {

    @Mock
    private AuthManager authManager;

    private HeaderProvider headerProvider;

    @BeforeEach
    void setUp() {
        headerProvider = new HeaderProvider(authManager);
    }

    @Test
    void getStockHeader() {
        // given
        HttpHeaders expected = new HttpHeaders();
        expected.set("tr_id", "CTPF1702R");
        given(authManager.createAuthHttpHeaders()).willReturn(new HttpHeaders());

        // when
        HttpHeaders result = headerProvider.getStockHeader();

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
