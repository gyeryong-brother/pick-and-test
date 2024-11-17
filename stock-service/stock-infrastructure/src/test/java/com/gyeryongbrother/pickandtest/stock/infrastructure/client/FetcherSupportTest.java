package com.gyeryongbrother.pickandtest.stock.infrastructure.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("api 요청을 도와준다")
class FetcherSupportTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FetcherSupport fetcherSupport;

    @Test
    @DisplayName("응답 코드가 200 ok 면 정상 작동한다")
    void ok() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();

        // when
        String result = fetcherSupport.get(
                "http://localhost:" + port + "/test/ok",
                httpHeaders,
                String.class
        ).getBody();

        // then
        assertThat(result).isEqualTo("ok");
    }

    @Test
    @DisplayName("응답 코드가 400 bad request 면 예외 메시지는 client error 이다")
    void badRequest() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();

        // when
        String result = assertThrows(IllegalArgumentException.class, () ->
                fetcherSupport.get(
                        "http://localhost:" + port + "/test/bad-request",
                        httpHeaders,
                        String.class
                )
        ).getMessage();

        // then
        assertThat(result).isEqualTo("client error");
    }

    @Test
    @DisplayName("응답 코드가 500 internal server error 면 예외 메시지는 server error 이다")
    void internalServerError() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();

        // when
        String result = assertThrows(IllegalStateException.class, () ->
                fetcherSupport.get(
                        "http://localhost:" + port + "/test/internal-server-error",
                        httpHeaders,
                        String.class
                )
        ).getMessage();

        // then
        assertThat(result).isEqualTo("server error");
    }
}
