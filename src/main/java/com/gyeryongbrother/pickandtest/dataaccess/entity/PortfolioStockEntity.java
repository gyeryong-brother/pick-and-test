package com.gyeryongbrother.pickandtest.dataaccess.entity;

import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.domain.core.Stock;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class PortfolioStockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PortfolioEntity portfolioEntity;

    private StockEntity stockEntity;
    private BigDecimal portion;
}
