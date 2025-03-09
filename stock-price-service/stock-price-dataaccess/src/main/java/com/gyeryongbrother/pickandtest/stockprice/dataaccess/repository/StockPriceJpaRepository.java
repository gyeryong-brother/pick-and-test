package com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceJpaRepository extends JpaRepository<StockPriceEntity, Long> {
}
