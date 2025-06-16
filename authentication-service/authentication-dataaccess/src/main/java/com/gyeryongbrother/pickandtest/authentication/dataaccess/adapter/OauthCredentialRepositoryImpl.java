package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper.OauthCredentialDataAccessMapper;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.repository.OauthCredentialJpaRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OauthCredentialRepositoryImpl implements OauthCredentialRepository {

    private final OauthCredentialJpaRepository oauthCredentialJpaRepository;
    private final OauthCredentialDataAccessMapper oauthCredentialDataAccessMapper;

    @Override
    public OauthCredential save(OauthCredential oauthCredential) {
        OauthCredentialEntity entity = oauthCredentialDataAccessMapper.toEntity(oauthCredential);
        OauthCredentialEntity savedEntity = oauthCredentialJpaRepository.save(entity);
        return savedEntity.toDomain();
    }
}
