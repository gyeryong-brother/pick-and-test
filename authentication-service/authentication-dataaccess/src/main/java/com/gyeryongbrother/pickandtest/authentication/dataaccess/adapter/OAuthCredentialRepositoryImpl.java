package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper.OauthCredentialDataAccessMapper;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.repository.OauthCredentialJpaRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OAuthCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OAuthCredentialRepositoryImpl implements OAuthCredentialRepository {

    private final OauthCredentialJpaRepository oauthCredentialJpaRepository;
    private final OauthCredentialDataAccessMapper oauthCredentialDataAccessMapper;

    @Override
    public OAuthCredential save(OAuthCredential oauthCredential) {
        OauthCredentialEntity entity = oauthCredentialDataAccessMapper.toEntity(oauthCredential);
        OauthCredentialEntity savedEntity = oauthCredentialJpaRepository.save(entity);
        return savedEntity.toDomain();
    }
}
