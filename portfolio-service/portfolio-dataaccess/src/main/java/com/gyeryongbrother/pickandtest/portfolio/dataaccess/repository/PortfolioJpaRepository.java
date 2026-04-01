package com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PortfolioJpaRepository extends JpaRepository<PortfolioEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM PortfolioEntity p WHERE p.id = :id")
    Optional<PortfolioEntity> findByIdWithPessimisticLock(@Param("id") Long id);
}
