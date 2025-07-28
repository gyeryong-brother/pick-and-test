package com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @OneToMany(mappedBy = "portfolioEntity", cascade = CascadeType.ALL)
    private List<PortfolioStockEntity> portfolioStockEntities;

    public void syncPortfolioStockEntities() {
        List<PortfolioStockEntity> portfolioStockEntities = this.portfolioStockEntities;
        for (PortfolioStockEntity portfolioStock : portfolioStockEntities) {
            portfolioStock.setPortfolioEntity(this);
        }
    }
}
