package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceJpaRepository extends JpaRepository<StockPriceEntity, Long> {
}
