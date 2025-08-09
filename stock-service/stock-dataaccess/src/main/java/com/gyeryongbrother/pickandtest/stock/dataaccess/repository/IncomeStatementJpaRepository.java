package com.gyeryongbrother.pickandtest.stock.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.IncomeStatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeStatementJpaRepository extends JpaRepository<IncomeStatementEntity, Long> {
}
