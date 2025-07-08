package com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import org.springframework.stereotype.Component;

@Component
public class OauthCredentialDataAccessMapper {

    public OauthCredentialEntity toEntity(OAuthCredential oauthCredential) {
        return OauthCredentialEntity.builder()
                .id(oauthCredential.id())
                .memberId(oauthCredential.memberId())
                .memberRole(oauthCredential.memberRole())
                .oAuthType(oauthCredential.oauthId().oAuthType())
                .oAuthId(oauthCredential.oauthId().value())
                .build();
    }
}
