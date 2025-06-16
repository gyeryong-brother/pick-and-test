package com.gyeryongbrother.pickandtest.authentication.dataaccess.repository;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthCredentialJpaRepository extends JpaRepository<OauthCredentialEntity, Long> {
}
