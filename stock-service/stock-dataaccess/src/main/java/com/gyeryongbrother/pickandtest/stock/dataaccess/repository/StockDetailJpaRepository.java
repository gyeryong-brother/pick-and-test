package com.gyeryongbrother.pickandtest.stock.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDetailJpaRepository extends JpaRepository<StockDetailEntity, Long> {
}
