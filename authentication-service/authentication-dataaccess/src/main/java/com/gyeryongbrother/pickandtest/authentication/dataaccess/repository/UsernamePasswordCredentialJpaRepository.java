package com.gyeryongbrother.pickandtest.authentication.dataaccess.repository;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.UsernamePasswordCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsernamePasswordCredentialJpaRepository extends JpaRepository<UsernamePasswordCredentialEntity, Long> {
}
