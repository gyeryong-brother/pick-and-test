package com.gyeryongbrother.pickandtest.stock.domain.service.exception;

import static com.gyeryongbrother.pickandtest.stock.domain.core.exception.StockCoreExceptionType.INVALID_YEAR;
import static com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceExceptionType.CAN_NOT_CALCULATE_COMPOUND_ANNUAL_GROWTH_RATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("도메인 예외를 매핑한다")
class CoreExceptionMapperTest {

    @Test
    @DisplayName("매퍼를 생성할 때 예외가 발생하지 않는다")
    void init() {
        // when & then
        assertThatCode(CoreExceptionMapper::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("연도가 유효하지 않을 때 연평균 성장률을 계산할 수 없는 예외로 변환한다")
    void map() {
        // given
        CoreExceptionMapper coreExceptionMapper = new CoreExceptionMapper();

        // when
        BaseExceptionType result = coreExceptionMapper.map(INVALID_YEAR);

        // then
        assertThat(result).isEqualTo(CAN_NOT_CALCULATE_COMPOUND_ANNUAL_GROWTH_RATE);
    }
}
