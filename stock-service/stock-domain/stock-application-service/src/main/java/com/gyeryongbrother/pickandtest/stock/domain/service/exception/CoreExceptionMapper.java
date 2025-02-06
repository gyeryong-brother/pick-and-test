package com.gyeryongbrother.pickandtest.stock.domain.service.exception;

import static com.gyeryongbrother.pickandtest.stock.domain.core.exception.StockCoreExceptionType.INVALID_YEAR;
import static com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceExceptionType.CAN_NOT_CALCULATE_COMPOUND_ANNUAL_GROWTH_RATE;
import static com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceExceptionType.CORE_EXCEPTION_NOT_REGISTERED;
import static com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceExceptionType.CORE_EXCEPTION_SHOULD_BE_REGISTERED;

import com.gyeryongbrother.pickandtest.stock.domain.core.exception.CoreExceptionType;
import com.gyeryongbrother.pickandtest.stock.domain.core.exception.StockCoreExceptionType;
import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CoreExceptionMapper {

    private final Map<CoreExceptionType, BaseExceptionType> values = Map.of(
            INVALID_YEAR, CAN_NOT_CALCULATE_COMPOUND_ANNUAL_GROWTH_RATE
    );

    public CoreExceptionMapper() {
        validate();
    }

    private void validate() {
        Arrays.stream(StockCoreExceptionType.values())
                .forEach(this::validate);
    }

    private void validate(StockCoreExceptionType exceptionType) {
        if (values.containsKey(exceptionType)) {
            return;
        }
        throw new StockServiceException(CORE_EXCEPTION_SHOULD_BE_REGISTERED);
    }

    public BaseExceptionType map(CoreExceptionType exceptionType) {
        if (values.containsKey(exceptionType)) {
            return values.get(exceptionType);
        }
        throw new StockServiceException(CORE_EXCEPTION_NOT_REGISTERED);
    }
}
