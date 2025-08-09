package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.StockDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "stock_detail")
public class StockDetailEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private StockEntity stock;

    private BigDecimal lastStockPrice;
    private BigDecimal compoundAnnualGrowthRate;
    private BigDecimal dividendYield;

    public StockDetail toDomain() {
        return StockDetail.builder()
                .id(id)
                .stockId(stock.getId())
                .lastStockPrice(lastStockPrice)
                .compoundAnnualGrowthRate(compoundAnnualGrowthRate)
                .dividendYield(dividendYield)
                .build();
    }
}
