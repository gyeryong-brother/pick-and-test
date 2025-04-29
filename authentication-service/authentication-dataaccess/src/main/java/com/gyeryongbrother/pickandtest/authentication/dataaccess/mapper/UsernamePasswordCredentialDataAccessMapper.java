package com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.UsernamePasswordCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordCredentialDataAccessMapper {

    public UsernamePasswordCredentialEntity toEntity(UsernamePasswordCredential usernamePasswordCredential) {
        return UsernamePasswordCredentialEntity.builder()
                .id(usernamePasswordCredential.id())
                .memberId(usernamePasswordCredential.memberId())
                .username(usernamePasswordCredential.username())
                .password(usernamePasswordCredential.password())
                .build();
    }
}
