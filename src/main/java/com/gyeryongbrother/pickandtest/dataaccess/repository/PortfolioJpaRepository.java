package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioJpaRepository extends JpaRepository<PortfolioEntity,Long> {
}
