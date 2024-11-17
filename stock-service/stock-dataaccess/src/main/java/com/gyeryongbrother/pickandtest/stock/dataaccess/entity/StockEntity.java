package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String symbol;

    @Enumerated(value = STRING)
    private StockExchange stockExchange;

    private Long outstandingShares;
    private LocalDate listingDate;

    @OneToMany(mappedBy = "stock", cascade = PERSIST)
    @Builder.Default
    private List<StockPriceEntity> stockPrices = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = PERSIST)
    @Builder.Default
    private List<DividendEntity> dividends = new ArrayList<>();

    @OneToMany(mappedBy = "stock", cascade = PERSIST)
    @Builder.Default
    private List<IncomeStatementEntity> incomeStatements = new ArrayList<>();

    public void addStockPrice(StockPriceEntity stockPriceEntity) {
        stockPrices.add(stockPriceEntity);
        stockPriceEntity.setStock(this);
    }

    public void addDividend(DividendEntity dividendEntity) {
        dividends.add(dividendEntity);
        dividendEntity.setStock(this);
    }
}
