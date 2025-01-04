package com.gyeryongbrother.pickandtest.stock.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividendJpaRepository extends JpaRepository<DividendEntity, Long> {
}
