package com.gyeryongbrother.pickandtest.domain.core;

import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.threeHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.BigDecimalFixture.twoHundred;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryFirst;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januarySecond;
import static com.gyeryongbrother.pickandtest.dataaccess.entity.LocalDateFixture.januaryThird;

public class DividendFixture {

    public static Dividend januaryFirstDividend(Long stockId) {
        return Dividend.builder()
                .stockId(stockId)
                .date(januaryFirst())
                .amount(oneHundred())
                .build();
    }

    public static Dividend januarySecondDividend(Long stockId) {
        return Dividend.builder()
                .stockId(stockId)
                .date(januarySecond())
                .amount(twoHundred())
                .build();
    }

    public static Dividend januaryThirdDividend(Long stockId) {
        return Dividend.builder()
                .stockId(stockId)
                .date(januaryThird())
                .amount(threeHundred())
                .build();
    }
}
