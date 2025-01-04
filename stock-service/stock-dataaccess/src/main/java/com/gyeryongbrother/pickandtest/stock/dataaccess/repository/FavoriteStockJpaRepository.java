package com.gyeryongbrother.pickandtest.stock.dataaccess.repository;

import com.gyeryongbrother.pickandtest.stock.dataaccess.entity.FavoriteStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteStockJpaRepository extends JpaRepository<FavoriteStockEntity, Long> {
}
