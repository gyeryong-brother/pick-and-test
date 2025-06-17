package com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import org.springframework.stereotype.Component;

@Component
public class OauthCredentialDataAccessMapper {

    public OauthCredentialEntity toEntity(OauthCredential oauthCredential) {
        return OauthCredentialEntity.builder()
                .id(oauthCredential.id())
                .memberId(oauthCredential.memberId())
                .authenticationMethod(oauthCredential.oauthId().authenticationMethod())
                .oauthServerId(oauthCredential.oauthId().oauthServerId())
                .build();
    }
}
