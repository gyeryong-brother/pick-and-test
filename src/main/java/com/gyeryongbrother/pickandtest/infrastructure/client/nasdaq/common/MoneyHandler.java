package com.gyeryongbrother.pickandtest.infrastructure.client.nasdaq.common;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class MoneyHandler {

    private static final String DOLLAR_SIGN = "$";
    private static final int DOLLAR_SIGN_POSITION = 1;

    public static BigDecimal toBigDecimal(String money) {
        return new BigDecimal(removeDollarSign(money));
    }

    private static String removeDollarSign(String money) {
        if (money.startsWith(DOLLAR_SIGN)) {
            return money.substring(DOLLAR_SIGN_POSITION);
        }
        return money;
    }
}
