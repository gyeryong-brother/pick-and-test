package com.gyeryongbrother.pickandtest.stock.domain.service.dto;

import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneHundred;
import static com.gyeryongbrother.pickandtest.stock.domain.fixture.valueobject.BigDecimalFixture.oneThousand;

import java.math.BigDecimal;
import java.util.List;

public class FavoriteStockResponseFixture {

    public static List<FavoriteStockResponse> favoriteStockResponses() {
        return List.of(
                favoriteStockResponse(oneThousand(), BigDecimal.valueOf(58.49), BigDecimal.valueOf(0.2)),
                favoriteStockResponse(oneHundred(), BigDecimal.ZERO, BigDecimal.valueOf(0.5))
        );
    }

    private static FavoriteStockResponse favoriteStockResponse(
            BigDecimal price,
            BigDecimal compoundAnnualGrowthRate,
            BigDecimal dividendYield
    ) {
        return new FavoriteStockResponse(
                null,
                "Apple Inc.",
                "AAPL",
                price,
                compoundAnnualGrowthRate,
                dividendYield
        );
    }
}
