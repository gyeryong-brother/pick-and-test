package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.FavoriteStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteStockJpaRepository extends JpaRepository<FavoriteStockEntity, Long> {
}
