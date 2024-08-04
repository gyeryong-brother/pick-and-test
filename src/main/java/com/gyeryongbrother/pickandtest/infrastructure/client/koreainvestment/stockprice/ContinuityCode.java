package com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stockprice;

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
        throw new IllegalArgumentException("invalid code");
    }

    public boolean hasNext() {
        return hasNext;
    }
}
