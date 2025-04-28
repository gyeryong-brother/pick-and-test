package com.gyeryongbrother.pickandtest.dividend.domain.fixture.valueobject;

import static com.gyeryongbrother.pickandtest.dividend.domain.fixture.entity.DividendFixture.dividend;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.Dividends;
import java.util.List;

public class DividendsFixture {

    private DividendsFixture() {
    }

    public static Dividends appleDividendsAtVariousYear(Long stockId) {
        List<Dividend> dividends = List.of(
                dividend(stockId, 2020, 3, 0.22),
                dividend(stockId, 2020, 6, 0.23),
                dividend(stockId, 2021, 4, 0.32)
        );
        return Dividends.from(dividends);
    }

    public static Dividends appleDividendsAtVariousYear() {
        List<Dividend> dividends = List.of(
                dividend(2020, 3, 0.22),
                dividend(2020, 6, 0.23),
                dividend(2021, 4, 0.32)
        );
        return Dividends.from(dividends);
    }
}
