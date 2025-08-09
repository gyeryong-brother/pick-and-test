package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.UsernamePasswordCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper.UsernamePasswordCredentialDataAccessMapper;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.repository.UsernamePasswordCredentialJpaRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsernamePasswordCredentialRepositoryImpl implements UsernamePasswordCredentialRepository {

    private final UsernamePasswordCredentialDataAccessMapper usernamePasswordCredentialDataAccessMapper;
    private final UsernamePasswordCredentialJpaRepository usernamePasswordCredentialJpaRepository;

    @Override
    public UsernamePasswordCredential save(UsernamePasswordCredential usernamePasswordCredential) {
        UsernamePasswordCredentialEntity entity =
                usernamePasswordCredentialDataAccessMapper.toEntity(usernamePasswordCredential);
        return usernamePasswordCredentialJpaRepository.save(entity)
                .toDomain();
    }
}
