package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.IncomeStatementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeStatementJpaRepository extends JpaRepository<IncomeStatementEntity, Long> {
}
