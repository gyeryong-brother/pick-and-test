package com.gyeryongbrother.pickandtest.stock.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceJpaRepository extends JpaRepository<StockPriceEntity, Long> {
}
