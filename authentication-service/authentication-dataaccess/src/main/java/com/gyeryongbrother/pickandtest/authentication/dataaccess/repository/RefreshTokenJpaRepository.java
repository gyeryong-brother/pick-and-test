package com.gyeryongbrother.pickandtest.authentication.dataaccess.repository;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {

    void deleteByToken(String refreshToken);
}
