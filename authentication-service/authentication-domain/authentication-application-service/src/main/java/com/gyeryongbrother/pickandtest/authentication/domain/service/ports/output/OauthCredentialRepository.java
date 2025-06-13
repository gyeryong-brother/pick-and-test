package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;

public interface OauthCredentialRepository {

    OauthCredential save(OauthCredential oauthCredential);
}
