package com.gyeryongbrother.pickandtest.dataaccess.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.domain.core.StockExchange;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
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
public class StockEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String symbol;

    @Enumerated(value = STRING)
    private StockExchange stockExchange;

    private LocalDate listingDate;

    @OneToMany(mappedBy = "stock")
    private List<StockPriceEntity> stockPrices;

    @OneToMany(mappedBy = "stock")
    private List<DividendEntity> dividends;
}
