package com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStockJpaRepository extends JpaRepository<PortfolioStockEntity, Long> {

    void deleteAllByPortfolioEntity_Id(Long portfolioId);
}
