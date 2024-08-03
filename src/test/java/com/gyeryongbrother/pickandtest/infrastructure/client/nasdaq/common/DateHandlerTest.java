package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("nasdaq api 에서 사용되는 시간 포맷에 맞춰 타입을 변환한다")
class DateHandlerTest {

    @Test
    @DisplayName("api 응답 데이터를 Date 로 변환한다")
    void toDate() {
        // given
        LocalDate expected = LocalDate.of(1989, 5, 22);

        // when
        LocalDate result = DateHandler.toDate("05/22/1989");

        // then
        assertThat(result).isEqualTo(expected);
    }
}
