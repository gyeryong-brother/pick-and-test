package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioStockJpaRepository extends JpaRepository<PortfolioStockEntity,Long> {
}
