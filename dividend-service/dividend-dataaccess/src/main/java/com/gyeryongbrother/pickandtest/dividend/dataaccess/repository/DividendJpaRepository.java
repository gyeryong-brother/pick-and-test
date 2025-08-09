package com.gyeryongbrother.pickandtest.dividend.dataaccess.repository;

import com.gyeryongbrother.pickandtest.dividend.dataaccess.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividendJpaRepository extends JpaRepository<DividendEntity, Long> {
}
