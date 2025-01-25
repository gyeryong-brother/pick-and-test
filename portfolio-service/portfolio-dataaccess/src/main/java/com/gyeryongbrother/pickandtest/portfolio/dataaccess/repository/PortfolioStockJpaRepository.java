package com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStockJpaRepository extends JpaRepository<PortfolioStockEntity, Long> {

    void deleteAllByPortfolioEntity_Id(Long portfolioId);
}
