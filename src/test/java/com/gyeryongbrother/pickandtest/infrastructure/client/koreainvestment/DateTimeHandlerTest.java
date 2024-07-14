package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeHandlerTest {

    private DateTimeHandler dateTimeHandler;

    @BeforeEach
    void setUp() {
        dateTimeHandler = new DateTimeHandler();
    }

    @Test
    void parse() {
        // given
        LocalDateTime expected = LocalDateTime.of(2024, 7, 14, 14, 44, 10);

        // when
        LocalDateTime result = dateTimeHandler.parse("2024-07-14 14:44:10");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void toQueryParam() {
        // given
        LocalDate date = LocalDate.of(2024, 1, 1);
        String expected = "20240101";

        // when
        String result = dateTimeHandler.toQueryParam(date);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
