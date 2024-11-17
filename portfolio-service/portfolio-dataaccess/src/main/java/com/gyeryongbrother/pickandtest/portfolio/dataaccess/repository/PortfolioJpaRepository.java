package com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioJpaRepository extends JpaRepository<PortfolioEntity, Long> {
}
