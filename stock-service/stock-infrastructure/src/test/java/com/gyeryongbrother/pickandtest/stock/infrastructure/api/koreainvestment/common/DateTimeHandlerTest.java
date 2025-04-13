package com.gyeryongbrother.pickandtest.stock.infrastructure.api.koreainvestment.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("api 에서 사용되는 시간 포맷에 맞춰 타입을 변환한다")
class DateTimeHandlerTest {

    @Test
    @DisplayName("api 응답 데이터를 DateTime 으로 변환한다")
    void toDateTime() {
        // given
        LocalDateTime expected = LocalDateTime.of(2024, 7, 14, 14, 44, 10);

        // when
        LocalDateTime result = DateTimeHandler.toDateTime("2024-07-14 14:44:10");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("api 응답 데이터를 Date 로 변환한다")
    void toDateByString() {
        // given
        LocalDate expected = LocalDate.of(2024, 1, 1);

        // when
        LocalDate result = DateTimeHandler.toDate("20240101");

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Date 를 api 요청용 데이터로 변환한다")
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
