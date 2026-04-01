package com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.LocalStockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalStockJpaRepository extends JpaRepository<LocalStockEntity, Long> {
    Page<LocalStockEntity> findAll(Pageable pageable);
}
