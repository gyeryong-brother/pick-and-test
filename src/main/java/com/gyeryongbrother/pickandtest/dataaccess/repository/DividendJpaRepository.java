package com.gyeryongbrother.pickandtest.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dataaccess.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividendJpaRepository extends JpaRepository<DividendEntity, Long> {
}
