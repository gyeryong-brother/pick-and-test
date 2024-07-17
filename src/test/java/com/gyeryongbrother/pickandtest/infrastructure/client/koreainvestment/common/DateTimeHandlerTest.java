package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeHandlerTest {

    @Test
    void toDateTime() {
        // given
        LocalDateTime expected = LocalDateTime.of(2024, 7, 14, 14, 44, 10);

        // when
        LocalDateTime result = DateTimeHandler.toDateTime("2024-07-14 14:44:10");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void toDateByString() {
        // given
        LocalDate expected = LocalDate.of(2024, 1, 1);

        // when
        LocalDate result = DateTimeHandler.toDate("20240101");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void toDateByLocalDate() {
        // given
        LocalDate date = LocalDate.of(2024, 1, 1);
        String expected = "20240101";

        // when
        String result = DateTimeHandler.toDate(date);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
