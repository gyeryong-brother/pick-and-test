package com.gyeryongbrother.pickandtest.stockprice.infrastructure.api.koreainvestment.stockprice;

import static com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureExceptionType.INVALID_CONTINUITY_CODE;

import com.gyeryongbrother.pickandtest.stockprice.infrastructure.exception.StockPriceInfrastructureException;
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
        throw new StockPriceInfrastructureException(INVALID_CONTINUITY_CODE);
    }

    public boolean hasNext() {
        return hasNext;
    }
}
