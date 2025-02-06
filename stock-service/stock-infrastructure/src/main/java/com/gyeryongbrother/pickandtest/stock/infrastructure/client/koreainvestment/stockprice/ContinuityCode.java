package com.gyeryongbrother.pickandtest.stock.infrastructure.client.koreainvestment.stockprice;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.INVALID_CONTINUITY_CODE;

import com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureException;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ContinuityCode {

    NEXT(Set.of("F", "M"), true),
    END(Set.of("D", "E"), false),
    ;

    private final Set<String> codes;
    private final boolean hasNext;

    public static ContinuityCode from(String code) {
        if (NEXT.codes.contains(code)) {
            return NEXT;
        }
        if (END.codes.contains(code)) {
            return END;
        }
        throw new StockInfrastructureException(INVALID_CONTINUITY_CODE);
    }

    public boolean hasNext() {
        return hasNext;
    }
}
