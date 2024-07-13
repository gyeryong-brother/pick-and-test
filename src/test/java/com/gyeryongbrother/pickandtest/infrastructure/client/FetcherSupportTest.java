package com.gyeryongbrother.pickandtest.infrastructure.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FetcherSupportTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FetcherSupport fetcherSupport;

    @Test
    void ok() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();

        // when
        String result = fetcherSupport.get(
                "http://localhost:" + port + "/test/ok",
                httpHeaders,
                String.class
        );

        // then
        assertThat(result).isEqualTo("ok");
    }

    @Test
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
