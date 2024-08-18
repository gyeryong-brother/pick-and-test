package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStockJpaRepository extends JpaRepository<PortfolioStockEntity, Long> {
}
