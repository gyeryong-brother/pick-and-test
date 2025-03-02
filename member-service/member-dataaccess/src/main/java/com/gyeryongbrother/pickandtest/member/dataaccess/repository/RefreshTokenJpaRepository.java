package com.gyeryongbrother.pickandtest.member.dataaccess.repository;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.RefreshTokenEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity,Long> {

    List<RefreshTokenEntity> findByUsername(String username);
}
